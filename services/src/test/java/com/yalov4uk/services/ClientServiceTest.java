package com.yalov4uk.services;

import com.yalov4uk.interfaces.IClientService;
import com.yalov4uk.interfaces.beans.IUserService;
import com.yalov4uk.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * Created by valera on 5/6/17.
 */
@ContextConfiguration("/test_config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ClientServiceTest {

    @Autowired
    private IClientService clientService;
    @Autowired
    private IUserService userService;

    @Test
    @Rollback
    public void register() throws Exception {
        UserDto user = new UserDto("test", "test", "test");
        UserDto expectedUser = clientService.register(user);
        UserDto actualUser = userService.read(expectedUser.getId());
        assertEquals("User doesn't registered", expectedUser.getLogin(), actualUser.getLogin());
    }
}