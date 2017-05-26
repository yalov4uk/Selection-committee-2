package com.yalov4uk.interfaces.beans;

import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import dto.FacultyDto;
import dto.SubjectNameDto;
import dto.UserDto;

import java.util.List;

/**
 * Created by valera on 5/17/17.
 */
public interface IFacultyService extends IBaseCrudService<FacultyDto> {

    void addSubjectName(FacultyDto faculty, SubjectNameDto subjectName);

    void deleteSubjectName(FacultyDto faculty, SubjectNameDto subjectName);

    List<UserDto> getRegisteredUsers(FacultyDto facultyDto);
}
