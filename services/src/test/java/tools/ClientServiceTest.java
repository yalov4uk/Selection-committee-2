package tools;

import beans.User;
import interfaces.ICRUDService;
import interfaces.IClientService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by valera on 5/6/17.
 */
public class ClientServiceTest {

    private static IClientService clientService;
    private static ICRUDService crudService;

    @BeforeClass
    public static void init() throws Exception {
        clientService = new ClientService();
        crudService = new CRUDService();
    }

    @AfterClass
    public static void close() throws Exception {
        clientService = null;
        crudService = null;
    }

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