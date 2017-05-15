package com.yalov4uk.abstracts;

import com.yalov4uk.interfaces.IBaseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by valera on 5/3/17.
 */
@Transactional(rollbackFor = Exception.class)
public abstract class BaseService implements IBaseService {
}
