package com.yalov4uk.validators;

import com.yalov4uk.abstracts.BaseDtoValidator;
import com.yalov4uk.dto.SubjectNameDto;
import com.yalov4uk.exceptions.ServiceUncheckedException;
import org.springframework.stereotype.Component;

@Component
public class SubjectNameValidator extends BaseDtoValidator<SubjectNameDto> {

    @Override
    public void validate(SubjectNameDto dto) {
        super.validate(dto);
        if (dto.getName() == null || dto.getName().length() < 2)
            throw new ServiceUncheckedException("not valid subject");
    }
}
