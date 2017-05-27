package com.yalov4uk.dao;

import com.yalov4uk.abstracts.BaseDao;
import com.yalov4uk.beans.Role;
import com.yalov4uk.interfaces.IRoleDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDao extends BaseDao<Role> implements IRoleDao {

    protected Class<Role> getBeanClass() {
        return Role.class;
    }

    protected Logger getLogger(){
        return LoggerFactory.getLogger(this.getClass());
    }
}
