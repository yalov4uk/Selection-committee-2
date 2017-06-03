package com.yalov4uk.validators;

import com.yalov4uk.abstracts.BaseDtoValidator;
import com.yalov4uk.dto.SubjectDto;
import com.yalov4uk.exceptions.ServiceUncheckedException;
import org.springframework.stereotype.Component;

@Component
public class SubjectValidator extends BaseDtoValidator<SubjectDto> {

    @Override
    public void validate(SubjectDto dto) {
        super.validate(dto);
        if (dto.getValue() == null || dto.getValue() > 100 || dto.getValue() < 0 ||
                dto.getSubjectName() == null || dto.getUser() == null)
            throw new ServiceUncheckedException("not valid subject");
    }
}
