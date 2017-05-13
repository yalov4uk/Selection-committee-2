package com.yalov4uk.abstracts;

import com.yalov4uk.beans.*;
import com.yalov4uk.configs.DataSourceConfig;
import com.yalov4uk.dao.*;
import com.yalov4uk.interfaces.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by valera on 5/3/17.
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes= DataSourceConfig.class)
public class BaseDaoTest {

    @Autowired
    private FacultyDao facultyDao;
    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private IStatementDao statementDao;
    @Autowired
    private ISubjectDao subjectDao;
    @Autowired
    private ISubjectNameDao subjectNameDao;
    @Autowired
    private IUserDao userDao;

    @Test
    public void persist() throws Exception {
        Role expectedRole = new Role("test");
        roleDao.persist(expectedRole);
        assertNotNull("Object id is null", expectedRole.getId());
        Role actualRole = roleDao.find(expectedRole.getId());
        assertEquals("Object id isn't null, but object not persisted", expectedRole, actualRole);
    }

    @Test
    public void update() throws Exception {
        Faculty expectedFaculty = new Faculty("test", 1);

        User user = new User("test", "test", "test");
        Role role = new Role("test");
        user.setRole(role);

        Statement statement = new Statement();
        statement.setFaculty(expectedFaculty);
        statement.setUser(user);

        facultyDao.persist(expectedFaculty);
        roleDao.persist(role);
        userDao.persist(user);
        statementDao.persist(statement);

        Faculty actualFaculty = new Faculty("test2", 2);
        statement.setFaculty(actualFaculty);
        statementDao.update(statement);

        String expectedName = statement.getFaculty().getName();
        assertEquals("Object not updated", expectedName, actualFaculty.getName());
    }

    @Test
    public void find() throws Exception {
        Subject expectedSubject = new Subject(34);

        SubjectName subjectName = new SubjectName("test");
        expectedSubject.setSubjectName(subjectName);

        User user = new User("test", "test", "test");
        Role role = new Role("test");
        user.setRole(role);
        expectedSubject.setUser(user);

        subjectNameDao.persist(subjectName);
        roleDao.persist(role);
        userDao.persist(user);
        subjectDao.persist(expectedSubject);

        Subject actualSubject = subjectDao.find(expectedSubject.getId());
        assertEquals("Object not found", expectedSubject, actualSubject);
        assertEquals("Object found, but references not found", expectedSubject.getUser(),
                actualSubject.getUser());
    }

    @Test
    public void delete() throws Exception {
        SubjectName expectedSubjectName = new SubjectName("test");
        subjectNameDao.persist(expectedSubjectName);
        subjectNameDao.delete(expectedSubjectName.getId());
        SubjectName actualSubjectName = subjectNameDao.find(expectedSubjectName.getId());
        assertNull("Object not deleted", actualSubjectName);
    }

    @Test
    public void getAll() throws Exception {
        User user1 = new User("test1", "test1", "test1");
        User user2 = new User("test2", "test2", "test2");
        Role role1 = new Role("test1");
        Role role2 = new Role("test2");

        user1.setRole(role1);
        user2.setRole(role2);

        roleDao.persist(role1);
        roleDao.persist(role2);
        userDao.persist(user1);
        userDao.persist(user2);

        List<User> users = userDao.getAll();
        assertTrue("List<Object> doesn't contain added object1", users.contains(user1));
        assertTrue("List<Object> doesn't contain added object2", users.contains(user2));
    }

}