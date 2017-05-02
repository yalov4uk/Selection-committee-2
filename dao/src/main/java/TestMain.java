import beans.Faculty;
import beans.Role;
import dao.FacultyDao;
import dao.RoleDao;

import java.util.List;

/**
 * Created by valera on 4/30/17.
 */
public class TestMain {

    public static void main(String[] args) {
        FacultyDao facultyDao = new FacultyDao(Faculty.class);
        List<Faculty> results = facultyDao.getAll();
        results.forEach(faculty -> System.out.println(faculty.getName() + " " + faculty.getMaxSize()));

        facultyDao.findByName("fksis").getRequiredSubjects().forEach(
                subjectName -> System.out.println(subjectName.getName()));

        results = facultyDao.getAll();
        results.forEach(faculty -> System.out.println(faculty.getName() + " " + faculty.getMaxSize()));
    }
}
