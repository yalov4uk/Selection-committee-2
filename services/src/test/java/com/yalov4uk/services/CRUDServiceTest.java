package com.yalov4uk.services;

import com.yalov4uk.beans.Role;
import com.yalov4uk.beans.User;
import com.yalov4uk.interfaces.beans.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by valera on 5/6/17.
 */
@ContextConfiguration("/test_config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CRUDServiceTest {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserService userService;

    private User expectedUser;
    private Role expectedRole;
    private User actualUser;

    private User user1;
    private Role expectedRole1;
    private User user2;
    private Role expectedRole2;
    private Role actualRole;

    @Before
    public void setUp() throws Exception {
        expectedUser = new User("test", "test", "test");
        expectedRole = new Role("test");
        expectedUser.setRole(expectedRole);
    }

    @Test
    @Rollback
    public void create() throws Exception {
        roleService.create(expectedRole);
        assertNotNull("Object doesn't created", expectedRole.getId());

        userService.create(expectedUser);
        assertNotNull("Object doesn't created", expectedUser.getId());
    }

    @Test
    @Rollback
    public void read() throws Exception {
        createProc();
        actualUser = userService.read(expectedUser.getId());
        assertEquals("Object doesn't read", expectedUser, actualUser);
    }

    @Test
    @Rollback
    public void update() throws Exception {
        createProc();
        expectedUser.setName("test1");
        actualUser = userService.update(expectedUser);
        assertEquals("Object doesn't updated", expectedUser, actualUser);
    }

    @Test
    @Rollback
    public void delete() throws Exception {
        createProc();
        userService.delete(expectedUser.getId());
        actualUser = userService.read(expectedUser.getId());
        assertNull("object doesn't deleted", actualUser);

        roleService.delete(expectedRole.getId());
        actualRole = roleService.read(expectedRole.getId());
        assertNull("object doesn't deleted", actualRole);
    }

    @Test
    @Rollback
    public void getAll() throws Exception {
        user1 = new User("test1", "test1", "test1");
        expectedRole1 = new Role("test1");
        user1.setRole(expectedRole1);

        roleService.create(expectedRole1);

        user2 = new User("test2", "test2", "test2");
        expectedRole2 = new Role("test2");
        user2.setRole(expectedRole2);

        roleService.create(expectedRole2);

        userService.create(user1);
        userService.create(user2);

        List<User> users = userService.getAll();
        assertNotNull("List in null", users);
        assertTrue("List doesn't contains user1", users.contains(user1));
        assertTrue("List doesn't contains user2", users.contains(user2));
    }

    private void createProc(){
        roleService.create(expectedRole);
        userService.create(expectedUser);
    }
}