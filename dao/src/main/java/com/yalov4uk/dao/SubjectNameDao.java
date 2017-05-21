package com.yalov4uk.dao;

import com.yalov4uk.abstracts.BaseDao;
import com.yalov4uk.beans.SubjectName;
import com.yalov4uk.interfaces.ISubjectNameDao;
import org.springframework.stereotype.Repository;

/**
 * Created by valera on 5/1/17.
 */
@Repository
public class SubjectNameDao extends BaseDao<SubjectName> implements ISubjectNameDao {

    protected Class<SubjectName> getBeanClass(){
        return SubjectName.class;
    }
}
