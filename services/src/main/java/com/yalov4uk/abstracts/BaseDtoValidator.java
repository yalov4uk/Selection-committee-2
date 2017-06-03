package com.yalov4uk.abstracts;

import com.yalov4uk.exceptions.ServiceUncheckedException;
import com.yalov4uk.interfaces.validators.DtoValidator;

public class BaseDtoValidator<D extends Dto> implements DtoValidator<D> {

    @Override
    public void validate(D dto) {
        if (dto == null) throw new ServiceUncheckedException("object is null");
    }
}
