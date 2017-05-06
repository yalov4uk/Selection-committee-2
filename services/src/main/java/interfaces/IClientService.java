package interfaces;

import beans.User;

/**
 * Created by valera on 5/6/17.
 */
public interface IClientService extends IBaseService {

    User register(String name, String login, String password);

    User login(String login, String password);
}
