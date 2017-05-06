package factories.creators;

import beans.Statement;
import beans.User;
import dao.StatementDao;
import dao.UserDao;
import exceptions.DaoUncheckedException;
import interfaces.IBaseDao;
import interfaces.factories.IDaoCreator;

/**
 * Created by valera on 5/6/17.
 */
public class UserDaoCreator implements IDaoCreator {

    public IBaseDao create() {
        try {
            return new UserDao(User.class);
        } catch (Exception e) {
            throw new DaoUncheckedException(e);
        }
    }
}
