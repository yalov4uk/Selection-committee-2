package com.yalov4uk.controllers.beans;

import com.yalov4uk.beans.Statement;
import com.yalov4uk.abstracts.BaseCrudController;
import com.yalov4uk.dto.StatementDto;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import com.yalov4uk.interfaces.beans.IStatementService;
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
public class StatementController extends BaseCrudController<Statement, StatementDto> {

    private final IStatementService statementService;

    @Autowired
    public StatementController(IStatementService statementService) {
        this.statementService = statementService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody StatementDto dto) {
        return createCrud(dto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody StatementDto dto) {
        return updateCrud(dto);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestBody StatementDto dto) {
        return deleteCrud(dto);
    }

    protected IBaseCrudService<Statement> getService() {
        return statementService;
    }

    protected Class<Statement> getBeanClass() {
        return Statement.class;
    }

    protected Class<StatementDto> getDtoClass() {
        return StatementDto.class;
    }
}
