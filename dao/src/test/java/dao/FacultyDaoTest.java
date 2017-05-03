package dao;

import beans.Faculty;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.*;
import utils.HibernateUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by valera on 5/3/17.
 */
public class FacultyDaoTest {

    private static FacultyDao facultyDao;
    private static HibernateUtil hibernateUtil;
    private static Session session;

    private Transaction transaction;

    @BeforeClass
    public static void init() throws Exception {
        facultyDao = new FacultyDao(Faculty.class);
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
    }

    @Test
    public void findByName() throws Exception {
        Faculty faculty = new Faculty("test", 2);
        facultyDao.save(faculty);
        Faculty faculty2 = facultyDao.findByName(faculty.getName());
        assertNotNull("Faculty not found", faculty2);
        assertEquals("Faculty name doesn't match", faculty.getName(), faculty2.getName());
    }

}