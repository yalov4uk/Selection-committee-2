package com.yalov4uk.dao;

import com.yalov4uk.beans.Faculty;
import com.yalov4uk.configs.DataSourceConfig;
import com.yalov4uk.interfaces.IFacultyDao;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by valera on 5/3/17.
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = DataSourceConfig.class)
public class FacultyDaoTest {

    @Autowired
    private IFacultyDao facultyDao;

    @Test
    public void findByName() throws Exception {
        Faculty expectedFaculty = new Faculty("test", 2);
        facultyDao.persist(expectedFaculty);
        Faculty actualFaculty = facultyDao.findByName(expectedFaculty.getName());
        assertNotNull("Faculty not found", actualFaculty);
        assertEquals("Faculty name doesn't match", expectedFaculty.getName(), actualFaculty.getName());
    }

}