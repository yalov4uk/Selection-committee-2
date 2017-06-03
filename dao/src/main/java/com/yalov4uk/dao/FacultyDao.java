package com.yalov4uk.dao;

import com.yalov4uk.abstracts.BaseDao;
import com.yalov4uk.beans.Faculty;
import com.yalov4uk.interfaces.IFacultyDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class FacultyDao extends BaseDao<Faculty> implements IFacultyDao {

    protected Class<Faculty> getBeanClass() {
        return Faculty.class;
    }

    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }
}
