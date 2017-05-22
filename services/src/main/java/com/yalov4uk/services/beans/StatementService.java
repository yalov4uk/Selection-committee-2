package com.yalov4uk.services.beans;

import com.yalov4uk.abstracts.BaseCrudService;
import com.yalov4uk.beans.Statement;
import com.yalov4uk.exceptions.ServiceUncheckedException;
import com.yalov4uk.interfaces.IBaseDao;
import com.yalov4uk.interfaces.IStatementDao;
import com.yalov4uk.interfaces.beans.IStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by valera on 5/17/17.
 */
@Service
public class StatementService extends BaseCrudService<Statement> implements IStatementService {

    private final IStatementDao statementDao;

    @Autowired
    public StatementService(IStatementDao statementDao) {
        this.statementDao = statementDao;
    }

    protected IBaseDao<Statement> getDao(){
        return statementDao;
    }

    @Override
    public void create(Statement statement) {
        try {
            statementDao.persist(statement);

            statement.getFaculty().getStatements().add(statement);
            statement.getUser().getStatements().add(statement);

            logger.info("persisted");
        } catch (Exception e) {
            logger.error("not persisted");
            throw new ServiceUncheckedException(e);
        }
    }

    @Override
    public void delete(Integer key) {
        try {
            Statement statement = statementDao.find(key);
            statementDao.delete(key);

            statement.getUser().getStatements().remove(statement);
            statement.getFaculty().getStatements().remove(statement);

            logger.info("deleted");
        } catch (Exception e) {
            logger.error("not deleted");
            throw new ServiceUncheckedException(e);
        }
    }
}
