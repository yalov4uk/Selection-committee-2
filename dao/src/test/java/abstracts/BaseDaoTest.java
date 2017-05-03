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
        Role role = new Role("test");
        roleDao.persist(role);
        assertNotNull("Object id is null", role.getId());
        assertEquals("Object id isn't null, but object not persisted", roleDao.find(role.getId()), role);
    }

    @Test
    public void update() throws Exception {
        Faculty faculty = new Faculty("test", 1);

        User user = new User("test", "test", "test");
        Role role = new Role("test");
        user.setRole(role);

        Statement statement = new Statement();
        statement.setFaculty(faculty);
        statement.setUser(user);

        facultyDao.save(faculty);
        roleDao.save(role);
        userDao.save(user);
        statementDao.save(statement);

        Faculty newFaculty = new Faculty("test2", 2);
        statement.setFaculty(newFaculty);
        statementDao.update(statement);

        assertEquals("Object not updated", statement.getFaculty().getName(), "test2");
    }

    @Test
    public void find() throws Exception {
        Subject subject = new Subject(34);

        SubjectName subjectName = new SubjectName("test");
        subject.setSubjectName(subjectName);

        User user = new User("test", "test", "test");
        Role role = new Role("test");
        user.setRole(role);
        subject.setUser(user);

        subjectNameDao.save(subjectName);
        roleDao.save(role);
        userDao.save(user);
        Serializable subjectId = subjectDao.save(subject);

        Subject subject2 = subjectDao.find((Integer) subjectId);
        assertEquals("Object not found", subject, subject2);
        assertEquals("Object found, but references not found", subject.getUser(), subject2.getUser());
    }

    @Test
    public void delete() throws Exception {
        SubjectName subjectName = new SubjectName("test");
        Serializable subjectNameId = subjectNameDao.save(subjectName);
        subjectNameDao.delete((Integer) subjectNameId);
        assertNull("Object not deleted", subjectNameDao.find((Integer) subjectNameId));
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
        assertTrue("List<Object> doesn't contain added object2", users.contains(user1));
    }

}