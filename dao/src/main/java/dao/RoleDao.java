package dao;

import abstracts.BaseDao;
import beans.Role;
import interfaces.IRoleDao;

/**
 * Created by valera on 5/1/17.
 */
public class RoleDao extends BaseDao<Role> implements IRoleDao {

    public RoleDao(Class<Role> persistentClass) {
        super(persistentClass);
    }
}
