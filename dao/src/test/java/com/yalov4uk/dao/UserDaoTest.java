package com.yalov4uk.dao;

import com.yalov4uk.beans.Role;
import com.yalov4uk.beans.User;
import com.yalov4uk.configs.DataSourceConfig;
import com.yalov4uk.interfaces.IRoleDao;
import com.yalov4uk.interfaces.IUserDao;
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
@ContextConfiguration(classes= DataSourceConfig.class)
public class UserDaoTest {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private IRoleDao roleDao;

    @Test
    public void findByLogin() throws Exception {
        User expectedUser = new User("test", "test", "test");
        Role role = new Role("test");
        expectedUser.setRole(role);

        roleDao.persist(role);
        userDao.persist(expectedUser);
        User actualUser = userDao.findByLogin(expectedUser.getLogin());

        assertNotNull("User not found", actualUser);
        assertEquals("User name doesn't match", expectedUser.getLogin(), actualUser.getLogin());
    }

}