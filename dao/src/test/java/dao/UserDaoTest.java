package dao;

import beans.Role;
import beans.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.*;
import utils.HibernateUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by valera on 5/3/17.
 */
public class UserDaoTest {

    private static UserDao userDao;
    private static RoleDao roleDao;
    private static HibernateUtil hibernateUtil;
    private static Session session;

    private Transaction transaction;

    @BeforeClass
    public static void init() throws Exception {
        userDao = new UserDao(User.class);
        roleDao = new RoleDao(Role.class);
        hibernateUtil = HibernateUtil.getInstance();
        session = hibernateUtil.getSession();
    }

    @Before
    public void setUp() throws Exception {
        transaction = session.beginTransaction();
    }

    @After
    public void tearDown() throws Exception {
        transaction.rollback();
    }

    @AfterClass
    public static void close() throws Exception {
        userDao = null;
        roleDao = null;
    }

    @Test
    public void findByLogin() throws Exception {
        User expectedUser = new User("test", "test", "test");
        Role role = new Role("test");
        expectedUser.setRole(role);

        roleDao.save(role);
        userDao.save(expectedUser);
        User actualUser = userDao.findByLogin(expectedUser.getLogin());

        assertNotNull("User not found", actualUser);
        assertEquals("User name doesn't match", expectedUser.getLogin(), actualUser.getLogin());
    }

}