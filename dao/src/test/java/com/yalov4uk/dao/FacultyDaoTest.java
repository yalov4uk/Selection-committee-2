package com.yalov4uk.dao;

import com.yalov4uk.beans.Faculty;
import com.yalov4uk.interfaces.IFacultyDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by valera on 5/3/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest//(classes = Application.class)
public class FacultyDaoTest {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private IFacultyDao facultyDao;

    private EntityTransaction transaction = entityManager.getTransaction();

    @Before
    public void setUp() throws Exception {
        transaction.begin();
    }

    @After
    public void tearDown() throws Exception {
        transaction.rollback();
    }

    @Test
    public void findByName() throws Exception {
        Faculty expectedFaculty = new Faculty("test", 2);
        facultyDao.persist(expectedFaculty);
        Faculty actualFaculty = facultyDao.findByName(expectedFaculty.getName());
        assertNotNull("Faculty not found", actualFaculty);
        assertEquals("Faculty name doesn't match", expectedFaculty.getName(), actualFaculty.getName());
    }

}