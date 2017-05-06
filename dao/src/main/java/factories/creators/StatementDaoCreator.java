package factories.creators;

import beans.Role;
import beans.Statement;
import dao.RoleDao;
import dao.StatementDao;
import exceptions.DaoUncheckedException;
import interfaces.IBaseDao;
import interfaces.factories.IDaoCreator;

/**
 * Created by valera on 5/6/17.
 */
public class StatementDaoCreator implements IDaoCreator {

    public IBaseDao create() {
        try {
            return new StatementDao(Statement.class);
        } catch (Exception e) {
            throw new DaoUncheckedException(e);
        }
    }
}
