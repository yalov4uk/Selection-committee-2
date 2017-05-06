package factories.creators;

import beans.Role;
import dao.RoleDao;
import exceptions.DaoUncheckedException;
import interfaces.IBaseDao;
import interfaces.factories.IDaoCreator;

/**
 * Created by valera on 5/6/17.
 */
public class RoleDaoCreator implements IDaoCreator {

    public IBaseDao create() {
        try {
            return new RoleDao(Role.class);
        } catch (Exception e) {
            throw new DaoUncheckedException(e);
        }
    }
}
