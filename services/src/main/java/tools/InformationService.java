package tools;

import abstracts.BaseService;
import beans.*;
import dao.*;

import java.util.List;

/**
 * Created by valera on 5/3/17.
 */
public class InformationService extends BaseService {

    public List<Faculty> getFaculties() {
        /*Session session = hibernateUtil.getSession();
        Transaction transaction = null;
        List<Faculty> faculties = null;
        try {
            transaction = session.beginTransaction();

            FacultyDao facultyDao = new FacultyDao(Faculty.class);
            faculties = facultyDao.getAll();

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
        return faculties;*/
        //vs
        return baseCommand(() -> {
            FacultyDao facultyDao = new FacultyDao(Faculty.class);
            return facultyDao.getAll();
        });
    }

    public List<Role> getRoles(){
        return baseCommand(() -> {
            RoleDao roleDao = new RoleDao(Role.class);
            return roleDao.getAll();
        });
    }

    public List<Statement> getStatements(){
        return baseCommand(() -> {
            StatementDao statementDao = new StatementDao(Statement.class);
            return statementDao.getAll();
        });
    }

    public List<Subject> getSubjects(){
        return baseCommand(() -> {
            SubjectDao subjectDao = new SubjectDao(Subject.class);
            return subjectDao.getAll();
        });
    }

    public List<SubjectName> getSubjectNames(){
        return baseCommand(() -> {
            SubjectNameDao subjectNameDao = new SubjectNameDao(SubjectName.class);
            return subjectNameDao.getAll();
        });
    }

    public List<User> getUsers(){
        return baseCommand(() -> {
            UserDao userDao = new UserDao(User.class);
            return userDao.getAll();
        });
    }
}
