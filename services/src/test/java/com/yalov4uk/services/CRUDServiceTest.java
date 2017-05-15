package com.yalov4uk.services;

import com.yalov4uk.beans.Role;
import com.yalov4uk.beans.User;
import com.yalov4uk.interfaces.ICRUDService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by valera on 5/6/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest//(classes = Application.class)
public class CRUDServiceTest {

    @Autowired
    private ICRUDService crudService;

    private User expectedUser;
    private Role expectedRole;
    private Role actualRole;
    private User actualUser;

    private User user1;
    private Role expectedRole1;
    private Role actualRole1;
    private User user2;
    private Role expectedRole2;
    private Role actualRole2;

    @Before
    public void setUp() throws Exception {
        expectedUser = new User("test", "test", "test");
        expectedRole = new Role("test");
        expectedUser.setRole(expectedRole);
    }

    @Test
    public void create() throws Exception {
        try {
            actualRole = crudService.create(expectedRole);
            assertEquals("Object doesn't created", expectedRole, actualRole);

            actualUser = crudService.create(expectedUser);
            assertEquals("Object doesn't created", expectedUser, actualUser);
        } finally {
            clearDbAfterCreate();
        }
    }

    @Test
    public void read() throws Exception {
        try {
            create();
            actualUser = crudService.read(expectedUser.getId(), User.class);
            assertEquals("Object doesn't read", expectedUser, actualUser);
        } finally {
            clearDbAfterCreate();
        }
    }

    @Test
    public void update() throws Exception {
        try {
            create();
            expectedUser.setName("test1");
            actualUser = crudService.update(expectedUser);
            assertEquals("Object doesn't updated", expectedUser, actualUser);
        } finally {
            clearDbAfterCreate();
        }
    }

    @Test
    public void delete() throws Exception {
        try {
            create();
        } finally {
            crudService.delete(expectedUser);
            actualUser = crudService.read(expectedUser.getId(), User.class);
            assertNull("object doesn't deleted", actualUser);

            crudService.delete(expectedRole);
            actualRole = crudService.read(expectedRole.getId(), Role.class);
            assertNull("object doesn't deleted", actualRole);
        }
    }

    @Test
    public void getAll() throws Exception {
        try {
            user1 = new User("test1", "test1", "test1");
            expectedRole1 = new Role("test1");
            user1.setRole(expectedRole1);

            actualRole1 = crudService.create(expectedRole1);

            user2 = new User("test2", "test2", "test2");
            expectedRole2 = new Role("test2");
            user2.setRole(expectedRole2);

            actualRole2 = crudService.create(expectedRole2);

            crudService.create(user1);
            crudService.create(user2);

            List<User> users = crudService.getAll(User.class);
            assertNotNull("List in null", users);
            assertTrue("List doesn't contains user1", users.contains(user1));
            assertTrue("List doesn't contains user2", users.contains(user2));
        } finally {
            crudService.delete(user1);
            crudService.delete(user2);
            crudService.delete(expectedRole1);
            crudService.delete(expectedRole2);
        }
    }

    private void clearDbAfterCreate() {
        crudService.delete(expectedUser);
        actualUser = crudService.read(expectedUser.getId(), User.class);

        crudService.delete(expectedRole);
        actualRole = crudService.read(expectedRole.getId(), Role.class);
    }

}