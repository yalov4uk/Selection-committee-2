package com.yalov4uk.interfaces.beans;

import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import dto.FacultyDto;
import dto.UserDto;

import java.util.List;

/**
 * Created by valera on 5/17/17.
 */
public interface IFacultyService extends IBaseCrudService<FacultyDto> {

    void addSubjectName(Integer facultyId, Integer subjectNameId);

    void deleteSubjectName(Integer facultyId, Integer subjectNameId);

    List<UserDto> getRegisteredUsers(FacultyDto facultyDto);
}
