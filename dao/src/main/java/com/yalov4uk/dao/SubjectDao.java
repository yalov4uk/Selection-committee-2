package com.yalov4uk.dao;

import com.yalov4uk.abstracts.BaseDao;
import com.yalov4uk.beans.Subject;
import com.yalov4uk.interfaces.ISubjectDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectDao extends BaseDao<Subject> implements ISubjectDao {

    protected Class<Subject> getBeanClass() {
        return Subject.class;
    }

    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }
}
