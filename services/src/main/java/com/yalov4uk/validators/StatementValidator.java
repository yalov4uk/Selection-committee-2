package com.yalov4uk.validators;

import com.yalov4uk.abstracts.BaseDtoValidator;
import com.yalov4uk.dto.StatementDto;
import com.yalov4uk.exceptions.ServiceUncheckedException;
import org.springframework.stereotype.Component;

@Component
public class StatementValidator extends BaseDtoValidator<StatementDto> {

    @Override
    public void validate(StatementDto dto) {
        super.validate(dto);
        if (dto.getDate() == null || dto.getFaculty() == null || dto.getUser() == null)
            throw new ServiceUncheckedException("not valid statement");
    }
}
