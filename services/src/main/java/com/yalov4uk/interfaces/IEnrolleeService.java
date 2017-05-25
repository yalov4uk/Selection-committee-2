package com.yalov4uk.interfaces;

import com.yalov4uk.interfaces.abstracts.IBaseService;
import dto.FacultyDto;
import dto.SubjectNameDto;
import dto.UserDto;

import java.util.List;

/**
 * Created by valera on 5/6/17.
 */
public interface IEnrolleeService extends IBaseService {

    List<SubjectNameDto> getRequiredSubjectNames(UserDto user, FacultyDto faculty);

    boolean registerToFaculty(UserDto user, FacultyDto faculty);
}
