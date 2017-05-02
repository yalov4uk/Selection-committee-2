package dao;

import abstracts.BaseDao;
import beans.User;
import interfaces.IUserDao;

/**
 * Created by valera on 5/1/17.
 */
public class UserDao extends BaseDao<User> implements IUserDao {

    public UserDao(Class<User> persistentClass) {
        super(persistentClass);
    }
}
