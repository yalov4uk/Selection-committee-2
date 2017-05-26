package com.yalov4uk.services.beans;

import com.yalov4uk.abstracts.BaseCrudService;
import com.yalov4uk.beans.Faculty;
import com.yalov4uk.beans.SubjectName;
import com.yalov4uk.exceptions.NotFoundException;
import com.yalov4uk.exceptions.ServiceUncheckedException;
import com.yalov4uk.interfaces.IBaseDao;
import com.yalov4uk.interfaces.IFacultyDao;
import com.yalov4uk.interfaces.ISubjectNameDao;
import com.yalov4uk.interfaces.beans.IFacultyService;
import dto.FacultyDto;
import dto.SubjectNameDto;
import dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by valera on 5/17/17.
 */
@Service
public class FacultyService extends BaseCrudService<Faculty, FacultyDto> implements IFacultyService {

    private final IFacultyDao facultyDao;
    private final ISubjectNameDao subjectNameDao;

    @Autowired
    public FacultyService(IFacultyDao facultyDao, ISubjectNameDao subjectNameDao) {
        this.facultyDao = facultyDao;
        this.subjectNameDao = subjectNameDao;
    }

    public void addSubjectName(FacultyDto facultyDto, SubjectNameDto subjectNameDto) {
        try {
            Faculty faculty = facultyDao.find(facultyDto.getId());
            SubjectName subjectName = subjectNameDao.find(subjectNameDto.getId());
            if (faculty == null || subjectName == null || faculty.getRequiredSubjects().contains(subjectName)) {
                throw new NotFoundException();
            }

            faculty.getRequiredSubjects().add(subjectName);
        } catch (Exception e) {
            throw new ServiceUncheckedException(e);
        }
    }

    public void deleteSubjectName(FacultyDto facultyDto, SubjectNameDto subjectNameDto) {
        try {
            Faculty faculty = facultyDao.find(facultyDto.getId());
            SubjectName subjectName = subjectNameDao.find(subjectNameDto.getId());
            if (faculty == null || subjectName == null || !faculty.getRequiredSubjects().contains(subjectName)) {
                throw new NotFoundException();
            }

            faculty.getRequiredSubjects().remove(subjectName);
        } catch (Exception e) {
            throw new ServiceUncheckedException(e);
        }
    }

    public List<UserDto> getRegisteredUsers(FacultyDto facultyDto){
        try {
            Faculty faculty = facultyDao.find(facultyDto.getId());
            if (faculty == null) {
                throw new NotFoundException();
            }

            return faculty.getRegisteredUsers()
                    .stream()
                    .map(user -> modelMapper.map(user, UserDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceUncheckedException(e);
        }
    }

    protected IBaseDao<Faculty> getDao() {
        return facultyDao;
    }

    protected Class<Faculty> getBeanClass(){
        return Faculty.class;
    }

    protected Class<FacultyDto> getDtoClass(){
        return FacultyDto.class;
    }
}
