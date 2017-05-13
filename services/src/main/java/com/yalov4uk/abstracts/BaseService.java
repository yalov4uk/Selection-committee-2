package com.yalov4uk.abstracts;

import com.yalov4uk.exceptions.ServiceUncheckedException;
import com.yalov4uk.interfaces.IBaseService;
import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

/**
 * Created by valera on 5/3/17.
 */
@Transactional(rollbackFor = Exception.class)
public abstract class BaseService implements IBaseService {
}
