package com.yalov4uk.dao;

import com.yalov4uk.abstracts.BaseDao;
import com.yalov4uk.beans.Statement;
import com.yalov4uk.interfaces.IStatementDao;
import org.springframework.stereotype.Repository;

/**
 * Created by valera on 5/1/17.
 */
@Repository
public class StatementDao extends BaseDao<Statement> implements IStatementDao {

    public StatementDao() {
        super(Statement.class);
    }
}
