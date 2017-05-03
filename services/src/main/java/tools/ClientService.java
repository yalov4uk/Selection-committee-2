package tools;

import abstracts.BaseService;
import beans.Role;
import beans.User;
import dao.RoleDao;
import dao.UserDao;

/**
 * Created by valera on 5/3/17.
 */
public class ClientService extends BaseService {

    public User register(String name, String login, String password) {
        return baseCommand(() -> {
            UserDao userDao = new UserDao(User.class);
            if (userDao.findByLogin(login) != null){
                return null;
            }
            User user = new User(name, login, password);
            RoleDao roleDao = new RoleDao(Role.class);
            Role role = roleDao.find(1);
            user.setRole(role);
            userDao.persist(user);
            return user;
        });
    }

    public User login(String login, String password) {
        return baseCommand(() -> {
            UserDao userDao = new UserDao(User.class);
            User user = userDao.findByLogin(login);
            if (user == null || !user.getPassword().equals(password)){
                return null;
            }
            return user;
        });
    }
}
