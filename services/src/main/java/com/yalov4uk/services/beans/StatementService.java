package com.yalov4uk.services.beans;

import com.yalov4uk.abstracts.BaseCrudService;
import com.yalov4uk.beans.Statement;
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
}
