package com.yalov4uk.interfaces.beans;

import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import com.yalov4uk.dto.FacultyDto;
import com.yalov4uk.dto.UserDto;

import java.util.List;

public interface IFacultyService extends IBaseCrudService<FacultyDto> {

    void addSubjectName(Integer facultyId, Integer subjectNameId);

    void deleteSubjectName(Integer facultyId, Integer subjectNameId);

    List<UserDto> getRegisteredUsers(FacultyDto facultyDto);
}
