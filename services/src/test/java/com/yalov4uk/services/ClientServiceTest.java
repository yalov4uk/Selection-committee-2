package com.yalov4uk.services;

import com.yalov4uk.beans.User;
import com.yalov4uk.interfaces.ICRUDService;
import com.yalov4uk.interfaces.IClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by valera on 5/6/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest//(classes = Application.class)
public class ClientServiceTest {

    @Autowired
    private IClientService clientService;
    @Autowired
    private ICRUDService crudService;

    @Test
    public void register() throws Exception {
        User expectedUser = clientService.register("test", "test", "test");
        User actualUser = crudService.read(expectedUser.getId(), User.class);
        assertEquals("User doesn't registered", expectedUser, actualUser);
        crudService.delete(expectedUser);
    }

    @Test
    public void login() throws Exception {
        User expectedUser = clientService.register("test", "test", "test");
        User actualUser = clientService.login(expectedUser.getLogin(), expectedUser.getPassword());
        assertEquals("User doesn't registered", expectedUser, actualUser);
        crudService.delete(expectedUser);
    }

}