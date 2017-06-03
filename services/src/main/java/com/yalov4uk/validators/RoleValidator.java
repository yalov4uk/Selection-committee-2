package com.yalov4uk.validators;

import com.yalov4uk.abstracts.BaseDtoValidator;
import com.yalov4uk.dto.RoleDto;
import com.yalov4uk.exceptions.ServiceUncheckedException;
import org.springframework.stereotype.Component;

@Component
public class RoleValidator extends BaseDtoValidator<RoleDto> {

    @Override
    public void validate(RoleDto dto) {
        super.validate(dto);
        if (dto.getName() == null || dto.getName().length() < 2)
            throw new ServiceUncheckedException("not valid role");
    }
}
