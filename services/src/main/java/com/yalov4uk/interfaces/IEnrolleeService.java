package com.yalov4uk.interfaces;

import com.yalov4uk.dto.SubjectNameDto;
import com.yalov4uk.interfaces.abstracts.IBaseService;

import java.util.List;

public interface IEnrolleeService extends IBaseService {

    List<SubjectNameDto> getRequiredSubjectNames(Integer userId, Integer facultyId);

    void registerToFaculty(Integer userId, Integer facultyId);
}
