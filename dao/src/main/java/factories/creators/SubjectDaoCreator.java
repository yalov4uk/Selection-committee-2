package factories.creators;

import beans.Statement;
import beans.Subject;
import dao.StatementDao;
import dao.SubjectDao;
import exceptions.DaoUncheckedException;
import interfaces.IBaseDao;
import interfaces.factories.IDaoCreator;

/**
 * Created by valera on 5/6/17.
 */
public class SubjectDaoCreator implements IDaoCreator {

    public IBaseDao create() {
        try {
            return new SubjectDao(Subject.class);
        } catch (Exception e) {
            throw new DaoUncheckedException(e);
        }
    }
}
