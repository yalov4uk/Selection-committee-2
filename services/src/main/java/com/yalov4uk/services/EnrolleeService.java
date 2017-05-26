package com.yalov4uk.services;

import com.yalov4uk.abstracts.BaseService;
import com.yalov4uk.beans.*;
import com.yalov4uk.exceptions.NotFoundException;
import com.yalov4uk.exceptions.ServiceUncheckedException;
import com.yalov4uk.interfaces.IEnrolleeService;
import com.yalov4uk.interfaces.IFacultyDao;
import com.yalov4uk.interfaces.ISubjectDao;
import com.yalov4uk.interfaces.IUserDao;
import dto.FacultyDto;
import dto.SubjectNameDto;
import dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<SubjectNameDto> getRequiredSubjectNames(UserDto userDto, FacultyDto facultyDto) {
        try {
            User user = userDao.find(userDto.getId());
            Faculty faculty = facultyDao.find(facultyDto.getId());
            if (user == null || faculty == null || faculty.getRegisteredUsers().contains(user)) {
                throw new NotFoundException();
            }

            List<SubjectNameDto> subjectNames = new LinkedList<>();
            faculty.getRequiredSubjects()
                    .forEach(requiredSubject -> {
                        if (user.getSubjects()
                                .stream()
                                .map(Subject::getSubjectName)
                                .noneMatch(subjectName -> subjectName.equals(requiredSubject))) {
                            subjectNames.add(modelMapper.map(requiredSubject, SubjectNameDto.class));
                        }
                    });
            return subjectNames;
        } catch (Exception e) {
            throw new ServiceUncheckedException(e);
        }
    }

    public boolean registerToFaculty(UserDto userDto, FacultyDto facultyDto) {
        try {
            User user = userDao.find(userDto.getId());
            Faculty faculty = facultyDao.find(facultyDto.getId());
            if (user == null || faculty == null) {
                throw new NotFoundException();
            }
            List<Statement> statements = new ArrayList<>(faculty.getStatements());
            if (faculty.getRegisteredUsers().contains(user) || user.getStatements().stream().anyMatch(statements::contains)) {
                throw new RuntimeException();
            }

            Set<SubjectName> requiredSubjects = faculty.getRequiredSubjects();
            Set<SubjectName> userSubjectNames = user.getSubjects()
                    .stream()
                    .map(Subject::getSubjectName)
                    .collect(Collectors.toSet());
            if (requiredSubjects
                    .stream()
                    .allMatch(userSubjectNames::contains)) {
                faculty.getRegisteredUsers().add(user);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new ServiceUncheckedException(e);
        }
    }
}
