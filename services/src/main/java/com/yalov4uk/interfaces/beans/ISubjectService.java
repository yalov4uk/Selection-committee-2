package com.yalov4uk.interfaces.beans;

import com.yalov4uk.dto.SubjectDto;
import com.yalov4uk.dto.input.SubjectInputDto;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;

public interface ISubjectService extends IBaseCrudService<SubjectDto> {

    SubjectDto create(SubjectInputDto subjectInputDto);

    SubjectDto update(SubjectInputDto subjectInputDto);
}
