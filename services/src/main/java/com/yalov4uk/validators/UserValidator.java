package com.yalov4uk.validators;

import com.yalov4uk.abstracts.BaseDtoValidator;
import com.yalov4uk.dto.UserDto;
import com.yalov4uk.exceptions.ServiceUncheckedException;
import org.springframework.stereotype.Component;

@Component
public class UserValidator extends BaseDtoValidator<UserDto> {

    @Override
    public void validate(UserDto dto) {
        super.validate(dto);
        if (dto.getLogin() == null || dto.getLogin().length() < 2 || dto.getName() == null ||
                dto.getName().length() < 2 || dto.getPassword() == null || dto.getPassword().length() < 2)
            throw new ServiceUncheckedException("not valid user");
    }
}
