package com.yalov4uk.dao;

import com.yalov4uk.abstracts.BaseDao;
import com.yalov4uk.beans.Subject;
import com.yalov4uk.interfaces.ISubjectDao;
import org.springframework.stereotype.Repository;

/**
 * Created by valera on 5/1/17.
 */
@Repository
public class SubjectDao extends BaseDao<Subject> implements ISubjectDao {

    protected Class<Subject> getBeanClass(){
        return Subject.class;
    }
}
