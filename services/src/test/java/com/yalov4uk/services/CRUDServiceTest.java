package com.yalov4uk.services;

import com.yalov4uk.interfaces.beans.IRoleService;
import com.yalov4uk.interfaces.beans.IUserService;
import com.yalov4uk.dto.RoleDto;
import com.yalov4uk.dto.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    private UserDto expectedUser;
    private RoleDto expectedRole;
    private UserDto actualUser;

    private UserDto user1;
    private RoleDto expectedRole1;
    private UserDto user2;
    private RoleDto expectedRole2;

    @Before
    public void setUp() throws Exception {
        expectedUser = new UserDto("test", "test", "test");
        expectedRole = new RoleDto("test");
        expectedRole = roleService.create(expectedRole);
        expectedUser.setRole(expectedRole);
        expectedUser = userService.create(expectedUser);
    }

    @Test
    @Rollback
    public void create() throws Exception {
        assertNotNull("Object doesn't created", expectedRole);
        assertNotNull("Object doesn't created", expectedUser);
    }

    @Test
    @Rollback
    public void read() throws Exception {
        actualUser = userService.read(expectedUser.getId());
        assertEquals("Object doesn't read", expectedUser.getLogin(), actualUser.getLogin());
    }

    @Test
    @Rollback
    public void update() throws Exception {
        expectedUser.setName("test1");
        actualUser = userService.update(expectedUser);
        assertEquals("Object doesn't updated", expectedUser.getName(), actualUser.getName());
    }

    @Test
    @Rollback
    public void delete() throws Exception {
        userService.delete(expectedUser.getId());
        assertNull("User doesn't deleted", userService.read(expectedUser.getId()));
        roleService.delete(expectedRole.getId());
        assertNull("Role doesn't deleted", userService.read(expectedRole.getId()));
    }

    @Test
    @Rollback
    public void getAll() throws Exception {
        user1 = new UserDto("test1", "test1", "test1");
        expectedRole1 = new RoleDto("test1");
        expectedRole1 = roleService.create(expectedRole1);
        user1.setRole(expectedRole1);
        user1 = userService.create(user1);

        user2 = new UserDto("test2", "test2", "test2");
        expectedRole2 = new RoleDto("test2");
        expectedRole2 = roleService.create(expectedRole2);
        user2.setRole(expectedRole2);
        user2 = userService.create(user2);

        List<UserDto> users = userService.getAll();
        assertNotNull("List in null", users);
        assertTrue("List doesn't contains user1", users
                .stream()
                .map(UserDto::getLogin)
                .collect(Collectors.toList()).contains(user1.getLogin()));
        assertTrue("List doesn't contains user2", users
                .stream()
                .map(UserDto::getLogin)
                .collect(Collectors.toList()).contains(user2.getLogin()));
    }
}