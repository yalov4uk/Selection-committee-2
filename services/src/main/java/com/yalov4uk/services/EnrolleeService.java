package com.yalov4uk.services;

import com.yalov4uk.abstracts.BaseService;
import com.yalov4uk.beans.Faculty;
import com.yalov4uk.beans.Subject;
import com.yalov4uk.beans.SubjectName;
import com.yalov4uk.beans.User;
import com.yalov4uk.exceptions.ServiceUncheckedException;
import com.yalov4uk.interfaces.IEnrolleeService;
import com.yalov4uk.interfaces.IFacultyDao;
import com.yalov4uk.interfaces.ISubjectDao;
import com.yalov4uk.interfaces.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by valera on 5/3/17.
 */
@Service
public class EnrolleeService extends BaseService implements IEnrolleeService {

    private final ISubjectDao subjectDao;
    private final IFacultyDao facultyDao;
    private final IUserDao userDao;

    @Autowired
    public EnrolleeService(ISubjectDao subjectDao, IFacultyDao facultyDao, IUserDao userDao) {
        this.subjectDao = subjectDao;
        this.facultyDao = facultyDao;
        this.userDao = userDao;
    }

    public List<SubjectName> getRequiredSubjectNames(User user, Faculty faculty) {
        try {
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
            logger.info("got required subject names");
            return subjectNames;
        } catch (Exception e) {
            logger.error("not got required subject names");
            throw new ServiceUncheckedException(e);
        }
    }

    public boolean registerToFaculty(User user, Faculty faculty) {
        try {
            Set<SubjectName> requiredSubjects = faculty.getRequiredSubjects();
            Set<SubjectName> userSubjectNames = user.getSubjects()
                    .stream()
                    .map(Subject::getSubjectName)
                    .collect(Collectors.toSet());
            if (requiredSubjects
                    .stream()
                    .allMatch(userSubjectNames::contains)) {
                faculty.getRegisteredUsers().add(user);
                logger.info("registered to faculty");
                return true;
            }
            logger.error("not enough subjects");
            return false;
        } catch (Exception e) {
            logger.error("not registered to faculty");
            throw new ServiceUncheckedException(e);
        }
    }
}
