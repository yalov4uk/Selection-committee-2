package com.yalov4uk.dao;

import com.yalov4uk.abstracts.BaseDao;
import com.yalov4uk.beans.SubjectName;
import com.yalov4uk.interfaces.ISubjectNameDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectNameDao extends BaseDao<SubjectName> implements ISubjectNameDao {

    protected Class<SubjectName> getBeanClass() {
        return SubjectName.class;
    }

    protected Logger getLogger(){
        return LoggerFactory.getLogger(this.getClass());
    }
}
