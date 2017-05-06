package abstracts;

import beans.*;
import dao.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.*;
import utils.HibernateUtil;

import java.io.Serializable;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by valera on 5/3/17.
 */
public class BaseDaoTest {

    private static FacultyDao facultyDao;
    private static RoleDao roleDao;
    private static StatementDao statementDao;
    private static SubjectDao subjectDao;
    private static SubjectNameDao subjectNameDao;
    private static UserDao userDao;
    private static HibernateUtil hibernateUtil;
    private static Session session;

    private Transaction transaction;

    @BeforeClass
    public static void init() throws Exception {
        facultyDao = new FacultyDao(Faculty.class);
        roleDao = new RoleDao(Role.class);
        statementDao = new StatementDao(Statement.class);
        subjectDao = new SubjectDao(Subject.class);
        subjectNameDao = new SubjectNameDao(SubjectName.class);
        userDao = new UserDao(User.class);
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
        facultyDao = null;
        roleDao = null;
        statementDao = null;
        subjectDao = null;
        subjectNameDao = null;
        userDao = null;
        hibernateUtil = null;
        session = null;
    }

    @Test
    public void save() throws Exception {
        Faculty faculty = new Faculty("test", 1);
        Serializable facultyId = facultyDao.save(faculty);
        assertNotNull("Object id is null", facultyId);
        assertEquals("Object id isn't null, but object not saved", facultyId, faculty.getId());
    }

    @Test
    public void persist() throws Exception {
        Role expectedRole = new Role("test");
        roleDao.persist(expectedRole);
        assertNotNull("Object id is null", expectedRole.getId());
        Role actualRole = roleDao.find(expectedRole.getId());
        assertEquals("Object id isn't null, but object not persisted", expectedRole, actualRole);
    }

    @Test
    public void update() throws Exception {
        Faculty expectedFaculty = new Faculty("test", 1);

        User user = new User("test", "test", "test");
        Role role = new Role("test");
        user.setRole(role);

        Statement statement = new Statement();
        statement.setFaculty(expectedFaculty);
        statement.setUser(user);

        facultyDao.save(expectedFaculty);
        roleDao.save(role);
        userDao.save(user);
        statementDao.save(statement);

        Faculty actualFaculty = new Faculty("test2", 2);
        statement.setFaculty(actualFaculty);
        statementDao.update(statement);

        String expectedName = statement.getFaculty().getName();
        assertEquals("Object not updated", expectedName, actualFaculty.getName());
    }

    @Test
    public void find() throws Exception {
        Subject expectedSubject = new Subject(34);

        SubjectName subjectName = new SubjectName("test");
        expectedSubject.setSubjectName(subjectName);

        User user = new User("test", "test", "test");
        Role role = new Role("test");
        user.setRole(role);
        expectedSubject.setUser(user);

        subjectNameDao.save(subjectName);
        roleDao.save(role);
        userDao.save(user);
        Serializable subjectId = subjectDao.save(expectedSubject);

        Subject actualSubject = subjectDao.find((Integer) subjectId);
        assertEquals("Object not found", expectedSubject, actualSubject);
        assertEquals("Object found, but references not found", expectedSubject.getUser(),
                actualSubject.getUser());
    }

    @Test
    public void delete() throws Exception {
        SubjectName expectedSubjectName = new SubjectName("test");
        Serializable subjectNameId = subjectNameDao.save(expectedSubjectName);
        subjectNameDao.delete((Integer) subjectNameId);
        SubjectName actualSubjectName = subjectNameDao.find((Integer) subjectNameId);
        assertNull("Object not deleted", actualSubjectName);
    }

    @Test
    public void getAll() throws Exception {
        User user1 = new User("test1", "test1", "test1");
        User user2 = new User("test2", "test2", "test2");
        Role role1 = new Role("test1");
        Role role2 = new Role("test2");

        user1.setRole(role1);
        user2.setRole(role2);

        roleDao.save(role1);
        roleDao.save(role2);
        userDao.save(user1);
        userDao.save(user2);

        List<User> users = userDao.getAll();
        assertTrue("List<Object> doesn't contain added object1", users.contains(user1));
        assertTrue("List<Object> doesn't contain added object2", users.contains(user2));
    }

}