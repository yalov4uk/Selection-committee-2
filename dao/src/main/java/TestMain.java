import beans.*;
import dao.*;

import java.util.List;

/**
 * Created by valera on 4/30/17.
 */
public class TestMain {

    public static void main(String[] args) {
        FacultyDao facultyDao = new FacultyDao(Faculty.class);
        List<Faculty> results1 = facultyDao.getAll();
        results1.forEach(faculty -> System.out.println(faculty));
        results1.stream().findFirst().get().getRegisteredUsers().forEach(e -> System.out.println(e));
        results1.stream().findFirst().get().getRequiredSubjects().forEach(e -> System.out.println(e));

        RoleDao roleDao = new RoleDao(Role.class);
        List<Role> results2 = roleDao.getAll();
        results2.forEach(role -> System.out.println(role));
        results2.stream().findFirst().get().getUsers().forEach(e -> System.out.println(e));

        StatementDao statementDao = new StatementDao(Statement.class);
        List<Statement> results3 = statementDao.getAll();
        results3.forEach(statement -> System.out.println(statement));

        SubjectDao subjectDao = new SubjectDao(Subject.class);
        List<Subject> results4 = subjectDao.getAll();
        results4.forEach(subject -> System.out.println(subject));

        SubjectNameDao subjectNameDao = new SubjectNameDao(SubjectName.class);
        List<SubjectName> results5 = subjectNameDao.getAll();
        results5.forEach(subjectName -> System.out.println(subjectName));
        results5.stream().findFirst().get().getFaculties().forEach(e -> System.out.println(e));

        UserDao userDao = new UserDao(User.class);
        List<User> results6 = userDao.getAll();
        results6.forEach(user -> System.out.println(user));
        results6.stream().findFirst().get().getSubjects().forEach(e -> System.out.println(e));
        results6.stream().findFirst().get().getFaculties().forEach(e -> System.out.println(e));
    }
}
