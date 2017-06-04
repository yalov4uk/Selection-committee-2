package com.yalov4uk.services;

import com.yalov4uk.abstracts.BaseService;
import com.yalov4uk.beans.*;
import com.yalov4uk.dto.SubjectNameDto;
import com.yalov4uk.exceptions.ServiceUncheckedException;
import com.yalov4uk.interfaces.IEnrolleeService;
import com.yalov4uk.interfaces.IFacultyDao;
import com.yalov4uk.interfaces.IUserDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EnrolleeService extends BaseService implements IEnrolleeService {

    private final IFacultyDao facultyDao;
    private final IUserDao userDao;

    @Autowired
    public EnrolleeService(ModelMapper modelMapper, IFacultyDao facultyDao, IUserDao userDao) {
        super(modelMapper);
        this.facultyDao = facultyDao;
        this.userDao = userDao;
    }

    public List<SubjectNameDto> getRequiredSubjectNames(Integer userId, Integer facultyId) {
        User user = userDao.find(userId);
        Faculty faculty = facultyDao.find(facultyId);
        if (user == null || faculty == null || faculty.getRegisteredUsers().contains(user)) {
            throw new ServiceUncheckedException("wrong input or you already registered to this faculty");
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
    }

    public void registerToFaculty(Integer userId, Integer facultyId) {
        User user = userDao.find(userId);
        Faculty faculty = facultyDao.find(facultyId);
        if (user == null || faculty == null) {
            throw new ServiceUncheckedException("user or faculty not found");
        }
        List<Statement> statements = new ArrayList<>(faculty.getStatements());
        if (faculty.getRegisteredUsers().contains(user) || user.getStatements().stream().anyMatch(statements::contains)) {
            throw new ServiceUncheckedException("you already registered or have statement");
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
        } else {
            throw new ServiceUncheckedException("not enough requirement subjects");
        }
    }
}
