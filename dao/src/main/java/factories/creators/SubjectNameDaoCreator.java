package factories.creators;

import beans.Statement;
import beans.SubjectName;
import dao.StatementDao;
import dao.SubjectNameDao;
import exceptions.DaoUncheckedException;
import interfaces.IBaseDao;
import interfaces.factories.IDaoCreator;

/**
 * Created by valera on 5/6/17.
 */
public class SubjectNameDaoCreator implements IDaoCreator {

    public IBaseDao create() {
        try {
            return new SubjectNameDao(SubjectName.class);
        } catch (Exception e) {
            throw new DaoUncheckedException(e);
        }
    }
}
