package com.yalov4uk.services;

import com.yalov4uk.beans.Role;
import com.yalov4uk.beans.User;
import com.yalov4uk.interfaces.ICRUDService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by valera on 5/6/17.
 */
public class CRUDServiceTest {

    private static ICRUDService crudService;

    @BeforeClass
    public static void init() throws Exception {
        crudService = new CRUDService();
    }

    @AfterClass
    public static void close() throws Exception {
        crudService = null;
    }

    @Test
    public void crud() throws Exception {
        User expectedUser = new User("test", "test", "test");
        Role expectedRole = new Role("test");
        expectedUser.setRole(expectedRole);

        Role actualRole = crudService.create(expectedRole);
        assertEquals("Object doesn't created", expectedRole, actualRole);

        User actualUser = crudService.create(expectedUser);
        assertEquals("Object doesn't created", expectedUser, actualUser);

        actualUser = crudService.read(expectedUser.getId(), User.class);
        assertEquals("Object doesn't read", expectedUser, actualUser);

        expectedUser.setName("test1");
        actualUser = crudService.update(expectedUser);
        assertEquals("Object doesn't updated", expectedUser, actualUser);

        crudService.delete(expectedUser);
        actualUser = crudService.read(expectedUser.getId(), User.class);
        assertNull("object doesn't deleted", actualUser);

        crudService.delete(expectedRole);
        actualRole = crudService.read(expectedRole.getId(), Role.class);
        assertNull("object doesn't deleted", actualRole);
    }

    @Test
    public void getAll() throws Exception {
        User user1 = new User("test1", "test1", "test1");
        Role expectedRole1 = new Role("test1");
        user1.setRole(expectedRole1);

        Role actualRole1 = crudService.create(expectedRole1);
        assertEquals("Object doesn't created", expectedRole1, actualRole1);

        User user2 = new User("test2", "test2", "test2");
        Role expectedRole2 = new Role("test2");
        user2.setRole(expectedRole2);

        Role actualRole2 = crudService.create(expectedRole2);
        assertEquals("Object doesn't created", expectedRole2, actualRole2);

        crudService.create(user1);
        crudService.create(user2);

        List<User> users = crudService.getAll(User.class);
        assertNotNull("List in null", users);
        assertTrue("List doesn't contains user1", users.contains(user1));
        assertTrue("List doesn't contains user2", users.contains(user2));

        crudService.delete(user1);
        User actualUser1 = crudService.read(user1.getId(), User.class);
        assertNull("User1 doesn't deleted", actualUser1);

        crudService.delete(user2);
        User actualUser2 = crudService.read(user2.getId(), User.class);
        assertNull("User2 doesn't deleted", actualUser2);

        crudService.delete(expectedRole1);
        actualRole1 = crudService.read(expectedRole1.getId(), Role.class);
        assertNull("Role1 doesn't deleted", actualRole1);

        crudService.delete(expectedRole2);
        actualRole2 = crudService.read(expectedRole2.getId(), Role.class);
        assertNull("Role2 doesn't deleted", actualRole2);
    }

}