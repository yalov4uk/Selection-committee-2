package com.yalov4uk.services;

import com.yalov4uk.beans.Faculty;
import com.yalov4uk.beans.Role;
import com.yalov4uk.beans.SubjectName;
import com.yalov4uk.beans.User;
import com.yalov4uk.interfaces.IEnrolleeService;
import com.yalov4uk.interfaces.beans.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

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
    private ISubjectNameService subjectNameService;
    @Autowired
    private IUserService userService;

    private User user;
    private Role role;
    private Faculty faculty1;
    private Faculty faculty2;
    private SubjectName subjectName1;
    private SubjectName subjectName2;
    private SubjectName subjectName3;

    @Before
    public void setUp() throws Exception {
        user = new User("test", "test", "test");
        role = new Role("test");
        user.setRole(role);
        roleService.create(role);
        userService.create(user);

        faculty1 = new Faculty("test1", 10);
        faculty2 = new Faculty("test2", 8);

        Set<SubjectName> subjectNames1 = new HashSet<>();
        Set<SubjectName> subjectNames2 = new HashSet<>();

        subjectName1 = new SubjectName("test1");
        subjectName2 = new SubjectName("test2");
        subjectName3 = new SubjectName("test3");
        subjectNameService.create(subjectName1);
        subjectNameService.create(subjectName2);
        subjectNameService.create(subjectName3);

        subjectNames1.add(subjectName1);
        subjectNames1.add(subjectName2);

        subjectNames2.add(subjectName1);
        subjectNames2.add(subjectName2);
        subjectNames2.add(subjectName3);

        faculty1.setRequiredSubjects(subjectNames1);
        faculty2.setRequiredSubjects(subjectNames2);
        facultyService.create(faculty1);
        facultyService.create(faculty2);
    }

    @Test
    @Rollback
    public void getRequiredSubjectNames() throws Exception {
        List<SubjectName> actualSubjectNames1 = enrolleeService.getRequiredSubjectNames(user, faculty1);
        assertTrue("List doesn't contains subjectName1", actualSubjectNames1.contains(subjectName1));
        assertTrue("List doesn't contains subjectName2", actualSubjectNames1.contains(subjectName2));

        List<Integer> values1 = new ArrayList<>();
        values1.add(10);
        values1.add(20);
        enrolleeService.registerToFaculty(user, faculty1, actualSubjectNames1, values1);

        List<SubjectName> thisShouldBeNull = enrolleeService.getRequiredSubjectNames(user, faculty1);
        assertNull("Enrollee can twice register to the same faculty", thisShouldBeNull);

        List<SubjectName> actualSubjectNames2 = enrolleeService.getRequiredSubjectNames(user, faculty2);
        assertFalse("List contains subjectName1", actualSubjectNames2.contains(subjectName1));
        assertFalse("List contains subjectName2", actualSubjectNames2.contains(subjectName2));
        assertTrue("List doesn't contains subjectName3", actualSubjectNames2.contains(subjectName3));
    }

    @Test
    @Rollback
    public void registerToFaculty() throws Exception {
        List<SubjectName> actualSubjectNames1 = enrolleeService.getRequiredSubjectNames(user, faculty1);

        List<Integer> values1 = new ArrayList<>();
        values1.add(10);
        values1.add(20);
        enrolleeService.registerToFaculty(user, faculty1, actualSubjectNames1, values1);
        assertTrue("User doesn't registered to faculty1", faculty1.getRegisteredUsers().contains(user));

        List<SubjectName> actualSubjectNames2 = enrolleeService.getRequiredSubjectNames(user, faculty2);

        List<Integer> values2 = new ArrayList<>();
        values2.add(30);
        enrolleeService.registerToFaculty(user, faculty2, actualSubjectNames2, values2);
        assertTrue("User doesn't registered to faculty2", faculty2.getRegisteredUsers().contains(user));
    }
}