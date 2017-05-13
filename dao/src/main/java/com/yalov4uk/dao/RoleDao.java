package com.yalov4uk.dao;

import com.yalov4uk.abstracts.BaseDao;
import com.yalov4uk.beans.Role;
import com.yalov4uk.interfaces.IRoleDao;
import org.springframework.stereotype.Repository;

/**
 * Created by valera on 5/1/17.
 */
@Repository
public class RoleDao extends BaseDao<Role> implements IRoleDao {

    public RoleDao() {
        super(Role.class);
    }
}
