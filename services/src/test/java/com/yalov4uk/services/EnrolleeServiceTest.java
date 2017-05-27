package com.yalov4uk.services;

import com.yalov4uk.abstracts.Dto;
import com.yalov4uk.interfaces.IEnrolleeService;
import com.yalov4uk.interfaces.beans.*;
import com.yalov4uk.dto.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by valera on 5/6/17.
 */
@ContextConfiguration("/test_config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class EnrolleeServiceTest {

    @Autowired
    private IEnrolleeService enrolleeService;

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

    private UserDto user;
    private RoleDto role;
    private FacultyDto faculty1;
    private FacultyDto faculty2;
    private SubjectNameDto subjectName1;
    private SubjectNameDto subjectName2;
    private SubjectNameDto subjectName3;

    @Before
    public void setUp() throws Exception {
        user = new UserDto("test", "test", "test");
        role = roleService.create(new RoleDto("test"));
        user.setRole(role);
        user = userService.create(user);

        faculty1 = facultyService.create(new FacultyDto("test1", 10));
        faculty2 = facultyService.create(new FacultyDto("test2", 8));

        subjectName1 = subjectNameService.create(new SubjectNameDto("test1"));
        subjectName2 = subjectNameService.create(new SubjectNameDto("test2"));
        subjectName3 = subjectNameService.create(new SubjectNameDto("test3"));

        facultyService.addSubjectName(faculty1.getId(), subjectName1.getId());
        facultyService.addSubjectName(faculty1.getId(), subjectName2.getId());
        facultyService.addSubjectName(faculty2.getId(), subjectName1.getId());
        facultyService.addSubjectName(faculty2.getId(), subjectName2.getId());
        facultyService.addSubjectName(faculty2.getId(), subjectName3.getId());
    }

    @Test
    @Rollback
    public void getRequiredSubjectNames() throws Exception {
        List<SubjectNameDto> actualSubjectNames1 = enrolleeService.getRequiredSubjectNames(user, faculty1.getId());
        List<Integer> actualSubjectNames = actualSubjectNames1
                .stream()
                .map(Dto::getId)
                .collect(Collectors.toList());
        assertTrue("List doesn't contains subjectName1", actualSubjectNames.contains(subjectName1.getId()));
        assertTrue("List doesn't contains subjectName2", actualSubjectNames.contains(subjectName2.getId()));

        subjectService.create(new SubjectDto(10, actualSubjectNames1.get(0), user));
        subjectService.create(new SubjectDto(20, actualSubjectNames1.get(1), user));

        List<SubjectNameDto> thisShouldBeNull = enrolleeService.getRequiredSubjectNames(user, faculty1.getId());
        assertTrue("Enrollee can twice register to the same faculty", thisShouldBeNull.equals(new LinkedList<>()));

        List<SubjectNameDto> actualSubjectNames2 = enrolleeService.getRequiredSubjectNames(user, faculty2.getId());
        actualSubjectNames = actualSubjectNames2
                .stream()
                .map(Dto::getId)
                .collect(Collectors.toList());
        assertFalse("List contains subjectName1", actualSubjectNames.contains(subjectName1.getId()));
        assertFalse("List contains subjectName2", actualSubjectNames.contains(subjectName2.getId()));
        assertTrue("List doesn't contains subjectName3", actualSubjectNames.contains(subjectName3.getId()));
    }

    @Test(expected = RuntimeException.class)
    @Rollback
    public void registerToFaculty() throws Exception {
        List<SubjectNameDto> actualSubjectNames1 = enrolleeService.getRequiredSubjectNames(user, faculty1.getId());

        subjectService.create(new SubjectDto(10, actualSubjectNames1.get(0), user));
        subjectService.create(new SubjectDto(20, actualSubjectNames1.get(1), user));
        enrolleeService.registerToFaculty(user, faculty1.getId());
        List<Integer> registeredUserIds = facultyService.getRegisteredUsers(faculty1)
                .stream()
                .map(Dto::getId)
                .collect(Collectors.toList());
        assertTrue("User doesn't registered to faculty2", registeredUserIds.contains(user.getId()));

        List<SubjectNameDto> actualSubjectNames2 = enrolleeService.getRequiredSubjectNames(user, faculty2.getId());

        subjectService.create(new SubjectDto(30, actualSubjectNames2.get(0), user));

        enrolleeService.registerToFaculty(user, faculty2.getId());
        registeredUserIds = facultyService.getRegisteredUsers(faculty2)
                .stream()
                .map(Dto::getId)
                .collect(Collectors.toList());
        assertTrue("User doesn't registered to faculty2", registeredUserIds.contains(user.getId()));
        enrolleeService.registerToFaculty(user, faculty2.getId());
    }
}