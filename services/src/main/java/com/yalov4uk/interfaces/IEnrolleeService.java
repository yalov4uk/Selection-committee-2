package com.yalov4uk.interfaces;

import com.yalov4uk.interfaces.abstracts.IBaseService;
import com.yalov4uk.dto.SubjectNameDto;
import com.yalov4uk.dto.UserDto;

import java.util.List;

public interface IEnrolleeService extends IBaseService {

    List<SubjectNameDto> getRequiredSubjectNames(UserDto userDto, Integer facultyId);

    void registerToFaculty(UserDto userDto, Integer facultyId);
}
