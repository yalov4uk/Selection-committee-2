package com.yalov4uk.abstracts;

import com.yalov4uk.interfaces.abstracts.IBaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
public abstract class BaseService implements IBaseService {

    protected final ModelMapper modelMapper;

    @Autowired
    public BaseService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
