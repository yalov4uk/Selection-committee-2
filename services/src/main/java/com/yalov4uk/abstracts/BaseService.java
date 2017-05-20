package com.yalov4uk.abstracts;

import com.yalov4uk.interfaces.abstracts.IBaseService;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by valera on 5/3/17.
 */
@Transactional(rollbackFor = Exception.class)
public abstract class BaseService implements IBaseService {
    protected final static Logger logger = Logger.getLogger(BaseService.class);
}
