package com.yalov4uk.services;

import com.yalov4uk.beans.*;
import com.yalov4uk.interfaces.IAdminService;
import com.yalov4uk.interfaces.beans.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private IStatementService statementService;
    @Autowired
    private ISubjectService subjectService;
    @Autowired
    private ISubjectNameService subjectNameService;
    @Autowired
    private IUserService userService;

    private User user1;
    private User user2;
    private Role role;
    private SubjectName subjectName1;
    private SubjectName subjectName2;
    private Faculty faculty;

    @Before
    public void setUp() throws Exception {
        user1 = new User("test1", "test1", "test1");
        user2 = new User("test2", "test2", "test2");
        role = new Role("test");
        user1.setRole(role);
        user2.setRole(role);
        roleService.create(role);
        userService.create(user1);
        userService.create(user2);

        subjectName1 = new SubjectName("test1");
        subjectName2 = new SubjectName("test2");
        subjectNameService.create(subjectName1);
        subjectNameService.create(subjectName2);

        faculty = new Faculty("test", 10);
        Set<SubjectName> subjectNames = new HashSet<>();
        subjectNames.add(subjectName1);
        subjectNames.add(subjectName2);
        faculty.setRequiredSubjects(subjectNames);
        facultyService.create(faculty);

        Subject subject1 = new Subject(1);
        Subject subject2 = new Subject(5);
        Subject subject3 = new Subject(3);
        Subject subject4 = new Subject(4);

        subject1.setSubjectName(subjectName1);
        subject2.setSubjectName(subjectName2);
        subject3.setSubjectName(subjectName1);
        subject4.setSubjectName(subjectName2);

        subject1.setUser(user1);
        subject2.setUser(user1);
        subject3.setUser(user2);
        subject4.setUser(user2);

        subjectService.create(subject1);
        subjectService.create(subject2);
        subjectService.create(subject3);
        subjectService.create(subject4);

        user1.getSubjects().add(subject1);
        user1.getSubjects().add(subject2);
        user2.getSubjects().add(subject3);
        user2.getSubjects().add(subject4);

        userService.update(user1);
        userService.update(user2);
    }

    @Test
    @Rollback
    public void registerStatement() throws Exception {
        Statement statement1 = adminService.registerStatement(user1, faculty);
        assertEquals("Statement1 doesn't created", statement1.getUser(), user1);
        Statement statement2 = adminService.registerStatement(user2, faculty);
        assertEquals("Statement2 doesn't created", statement2.getUser(), user2);
    }

    @Test
    @Rollback
    public void calculateEntrants() throws Exception {
        registerStatement();
        List<Statement> statements = adminService.calculateEntrants(faculty);
        assertTrue("Size > faculty maxSize", statements.size() <= faculty.getMaxSize());
        assertEquals("Temp1.averageScore > temp2.averageScore", statements.get(0).getUser(), user2);
    }
}