package factories.creators;

import beans.Faculty;
import dao.FacultyDao;
import exceptions.DaoUncheckedException;
import interfaces.IBaseDao;
import interfaces.factories.IDaoCreator;

/**
 * Created by valera on 5/6/17.
 */
public class FacultyDaoCreator implements IDaoCreator {

    public IBaseDao create() {
        try {
            return new FacultyDao(Faculty.class);
        } catch (Exception e) {
            throw new DaoUncheckedException(e);
        }
    }
}
