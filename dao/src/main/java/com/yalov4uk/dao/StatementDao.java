package com.yalov4uk.dao;

import com.yalov4uk.abstracts.BaseDao;
import com.yalov4uk.beans.Statement;
import com.yalov4uk.interfaces.IStatementDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class StatementDao extends BaseDao<Statement> implements IStatementDao {

    protected Class<Statement> getBeanClass() {
        return Statement.class;
    }

    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }
}
