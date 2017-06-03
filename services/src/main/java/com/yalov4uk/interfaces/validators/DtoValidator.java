package com.yalov4uk.interfaces.validators;

import com.yalov4uk.abstracts.Dto;

public interface DtoValidator<D extends Dto> {

    void validate(D dto);
}
