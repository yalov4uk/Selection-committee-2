package com.yalov4uk.services;

import com.yalov4uk.beans.*;
import com.yalov4uk.interfaces.IEnrolleeService;
import com.yalov4uk.interfaces.beans.*;
import dto.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Autowired
    private ModelMapper modelMapper;

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
        roleService.create(modelMapper.map(role, RoleDto.class));
        userService.create(modelMapper.map(user, UserDto.class));

        faculty1 = new Faculty("test1", 10);
        faculty2 = new Faculty("test2", 8);

        Set<SubjectName> subjectNames1 = new HashSet<>();
        Set<SubjectName> subjectNames2 = new HashSet<>();

        subjectName1 = new SubjectName("test1");
        subjectName2 = new SubjectName("test2");
        subjectName3 = new SubjectName("test3");
        subjectNameService.create(modelMapper.map(subjectName1, SubjectNameDto.class));
        subjectNameService.create(modelMapper.map(subjectName2, SubjectNameDto.class));
        subjectNameService.create(modelMapper.map(subjectName3, SubjectNameDto.class));

        subjectNames1.add(subjectName1);
        subjectNames1.add(subjectName2);

        subjectNames2.add(subjectName1);
        subjectNames2.add(subjectName2);
        subjectNames2.add(subjectName3);

        faculty1.setRequiredSubjects(subjectNames1);
        faculty2.setRequiredSubjects(subjectNames2);
        facultyService.create(modelMapper.map(faculty1, FacultyDto.class));
        facultyService.create(modelMapper.map(faculty2, FacultyDto.class));
    }

    @Test
    @Rollback
    public void getRequiredSubjectNames() throws Exception {
        List<SubjectNameDto> actualSubjectNames1 = enrolleeService.getRequiredSubjectNames(modelMapper.map(user, UserDto.class), modelMapper.map(faculty1, FacultyDto.class));
        assertTrue("List doesn't contains subjectName1", actualSubjectNames1.contains(modelMapper.map(subjectName1, SubjectNameDto.class)));
        assertTrue("List doesn't contains subjectName2", actualSubjectNames1.contains(modelMapper.map(subjectName2, SubjectNameDto.class)));

        List<Integer> values1 = new ArrayList<>();
        values1.add(10);
        values1.add(20);

        Subject subject1 = new Subject(values1.get(0));
        subject1.setSubjectName(modelMapper.map(actualSubjectNames1.get(0), SubjectName.class));
        subject1.setUser(user);
        subjectService.create(modelMapper.map(subject1, SubjectDto.class));
        Subject subject2 = new Subject(values1.get(1));
        subject2.setSubjectName(modelMapper.map(actualSubjectNames1.get(1), SubjectName.class));
        subject2.setUser(user);
        subjectService.create(modelMapper.map(subject2, SubjectDto.class));

        assertTrue("Not registered", enrolleeService.registerToFaculty(modelMapper.map(user, UserDto.class), modelMapper.map(faculty1, FacultyDto.class)));

        List<SubjectNameDto> thisShouldBeNull = enrolleeService.getRequiredSubjectNames(modelMapper.map(user, UserDto.class), modelMapper.map(faculty1, FacultyDto.class));
        assertTrue("Enrollee can twice register to the same faculty", thisShouldBeNull.equals(new LinkedList<>()));

        List<SubjectNameDto> actualSubjectNames2 = enrolleeService.getRequiredSubjectNames(modelMapper.map(user, UserDto.class), modelMapper.map(faculty2, FacultyDto.class));
        assertFalse("List contains subjectName1", actualSubjectNames2.contains(modelMapper.map(subjectName1, SubjectNameDto.class)));
        assertFalse("List contains subjectName2", actualSubjectNames2.contains(modelMapper.map(subjectName2, SubjectNameDto.class)));
        assertTrue("List doesn't contains subjectName3", actualSubjectNames2.contains(modelMapper.map(subjectName3, SubjectNameDto.class)));
    }

    @Test
    @Rollback
    public void registerToFaculty() throws Exception {
        List<SubjectNameDto> actualSubjectNames1 = enrolleeService.getRequiredSubjectNames(modelMapper.map(user, UserDto.class), modelMapper.map(faculty1, FacultyDto.class));

        List<Integer> values1 = new ArrayList<>();
        values1.add(10);
        values1.add(20);

        Subject subject1 = new Subject(values1.get(0));
        subject1.setSubjectName(modelMapper.map(actualSubjectNames1.get(0), SubjectName.class));
        subject1.setUser(user);
        subjectService.create(modelMapper.map(subject1, SubjectDto.class));
        Subject subject2 = new Subject(values1.get(1));
        subject2.setSubjectName(modelMapper.map(actualSubjectNames1.get(1), SubjectName.class));
        subject2.setUser(user);
        subjectService.create(modelMapper.map(subject2, SubjectDto.class));

        enrolleeService.registerToFaculty(modelMapper.map(user, UserDto.class), modelMapper.map(faculty1, FacultyDto.class));
        assertTrue("User doesn't registered to faculty1", faculty1.getRegisteredUsers().contains(user));

        List<SubjectNameDto> actualSubjectNames2 = enrolleeService.getRequiredSubjectNames(modelMapper.map(user, UserDto.class), modelMapper.map(faculty2, FacultyDto.class));

        List<Integer> values2 = new ArrayList<>();
        values2.add(30);

        Subject subject3 = new Subject(values2.get(0));
        subject3.setSubjectName(modelMapper.map(actualSubjectNames2.get(0), SubjectName.class));
        subject3.setUser(user);
        subjectService.create(modelMapper.map(subject3, SubjectDto.class));

        enrolleeService.registerToFaculty(modelMapper.map(user, UserDto.class), modelMapper.map(faculty2, FacultyDto.class));
        assertTrue("User doesn't registered to faculty2", faculty2.getRegisteredUsers().contains(user));
    }
}