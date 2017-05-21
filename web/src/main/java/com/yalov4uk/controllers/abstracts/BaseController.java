package com.yalov4uk.controllers.abstracts;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by valera on 5/21/17.
 */
public abstract class BaseController {

    protected final static Logger logger = Logger.getLogger(BaseController.class);

    @Autowired
    protected ModelMapper modelMapper;
}
