package com.yalov4uk.services;

import com.yalov4uk.interfaces.IAdminService;
import com.yalov4uk.interfaces.IEnrolleeService;
import com.yalov4uk.interfaces.beans.*;
import dto.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by valera on 5/6/17.
 */
@ContextConfiguration("/test_config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AdminServiceTest {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IFacultyService facultyService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private ISubjectService subjectService;
    @Autowired
    private ISubjectNameService subjectNameService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IEnrolleeService enrolleeService;

    private UserDto user1;
    private UserDto user2;
    private RoleDto role;
    private SubjectNameDto subjectName1;
    private SubjectNameDto subjectName2;
    private FacultyDto faculty;

    @Before
    public void setUp() throws Exception {
        user1 = new UserDto("test1", "test1", "test1");
        user2 = new UserDto("test2", "test2", "test2");
        role = new RoleDto("test");
        role = roleService.create(role);
        user1.setRole(role);
        user2.setRole(role);
        user1 = userService.create(user1);
        user2 = userService.create(user2);

        subjectName1 = new SubjectNameDto("test1");
        subjectName2 = new SubjectNameDto("test2");
        subjectName1 = subjectNameService.create(subjectName1);
        subjectName2 = subjectNameService.create(subjectName2);

        faculty = new FacultyDto("test", 10);
        faculty = facultyService.create(faculty);
        facultyService.addSubjectName(faculty.getId(), subjectName1.getId());
        facultyService.addSubjectName(faculty.getId(), subjectName2.getId());

        subjectService.create(new SubjectDto(1, subjectName1, user1));
        subjectService.create(new SubjectDto(5, subjectName2, user1));
        subjectService.create(new SubjectDto(3, subjectName1, user2));
        subjectService.create(new SubjectDto(4, subjectName2, user2));
    }

    @Test
    @Rollback
    public void registerStatement() throws Exception {
        enrolleeService.registerToFaculty(user1, faculty.getId());
        StatementDto statement1 = adminService.registerStatement(user1.getId(), faculty.getId());
        assertEquals("Statement1 doesn't created", statement1.getUser().getId(), user1.getId());
        enrolleeService.registerToFaculty(user2, faculty.getId());
        StatementDto statement2 = adminService.registerStatement(user2.getId(), faculty.getId());
        assertEquals("Statement2 doesn't created", statement2.getUser().getId(), user2.getId());
    }

    @Test
    @Rollback
    public void calculateEntrants() throws Exception {
        enrolleeService.registerToFaculty(user1, faculty.getId());
        adminService.registerStatement(user1.getId(), faculty.getId());
        enrolleeService.registerToFaculty(user2, faculty.getId());
        adminService.registerStatement(user2.getId(), faculty.getId());
        List<StatementDto> statements = adminService.calculateEntrants(faculty.getId());
        assertTrue("Size > faculty maxSize", statements.size() <= faculty.getMaxSize());
        assertEquals("Temp1.averageScore > temp2.averageScore", statements.get(0).getUser().getId(), user2.getId());
    }
}