package tools;

import abstracts.BaseService;
import beans.Faculty;
import beans.Subject;
import beans.SubjectName;
import beans.User;
import dao.FacultyDao;
import dao.SubjectDao;
import dao.SubjectNameDao;
import dao.UserDao;
import exceptions.ServiceUncheckedException;
import factories.DaoFactory;
import interfaces.IEnrolleeService;
import interfaces.IFacultyDao;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by valera on 5/3/17.
 */
public class EnrolleeService extends BaseService implements IEnrolleeService {

    public List<SubjectName> getRequiredSubjectNames(User user, Faculty faculty) {
        if (faculty == null || faculty.getRegisteredUsers().contains(user)) {
            return null;
        }
        List<SubjectName> subjectNames = new LinkedList<>();
        faculty.getRequiredSubjects()
                .forEach(requiredSubject -> {
                    if (user.getSubjects()
                            .stream()
                            .map(Subject::getSubjectName)
                            .noneMatch(subjectName -> subjectName.equals(requiredSubject))) {
                        subjectNames.add(requiredSubject);
                    }
                });
        return subjectNames;
    }

    public Boolean registerToFaculty(User user, Faculty faculty, List<SubjectName> subjectNames,
                                     List<Integer> values) {
        return baseCommand(() -> {
            if (faculty == null) {
                return false;
            }
            SubjectDao subjectDao = new SubjectDao(Subject.class);
            for (int i = 0; i < subjectNames.size() && i < values.size(); i++) {
                Subject subject = new Subject(values.get(i));
                subject.setSubjectName(subjectNames.get(i));
                subject.setUser(user);
                subjectDao.persist(subject);

                user.getSubjects().add(subject);
                UserDao userDao = new UserDao(User.class);
                userDao.update(user);
            }
            FacultyDao facultyDao = new FacultyDao(Faculty.class);
            faculty.getRegisteredUsers().add(user);
            facultyDao.update(faculty);
            return true;
        });
    }
}
