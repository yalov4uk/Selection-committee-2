package dao;

import abstracts.BaseDao;
import beans.Statement;
import interfaces.IStatementDao;

/**
 * Created by valera on 5/1/17.
 */
public class StatementDao extends BaseDao<Statement> implements IStatementDao {

    public StatementDao(Class<Statement> persistentClass) {
        super(persistentClass);
    }
}
