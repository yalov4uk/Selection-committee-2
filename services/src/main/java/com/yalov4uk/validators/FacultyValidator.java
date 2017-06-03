package com.yalov4uk.validators;

import com.yalov4uk.abstracts.BaseDtoValidator;
import com.yalov4uk.dto.FacultyDto;
import com.yalov4uk.exceptions.ServiceUncheckedException;
import org.springframework.stereotype.Component;

@Component
public class FacultyValidator extends BaseDtoValidator<FacultyDto> {

    @Override
    public void validate(FacultyDto dto) {
        super.validate(dto);
        if (dto.getName() == null || dto.getName().length() < 2 || dto.getMaxSize() == null)
            throw new ServiceUncheckedException("not valid faculty");
    }
}
