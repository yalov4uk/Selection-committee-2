package com.yalov4uk.controllers.beans;

import com.yalov4uk.abstracts.BaseCrudController;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import com.yalov4uk.interfaces.beans.IStatementService;
import dto.StatementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by valera on 5/17/17.
 */
@RestController
@RequestMapping(value = "/statements")
public class StatementController extends BaseCrudController<StatementDto> {

    private final IStatementService statementService;

    @Autowired
    public StatementController(IStatementService statementService) {
        this.statementService = statementService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody StatementDto statement) {
        return createCrud(statement);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody StatementDto statement) {
        return updateCrud(statement);
    }

    protected IBaseCrudService<StatementDto> getService() {
        return statementService;
    }
}
