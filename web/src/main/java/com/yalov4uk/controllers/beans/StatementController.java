package com.yalov4uk.controllers.beans;

import com.yalov4uk.abstracts.BaseCrudController;
import com.yalov4uk.dto.StatementDto;
import com.yalov4uk.dto.UserAndFacultyDto;
import com.yalov4uk.interfaces.IAdminService;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import com.yalov4uk.interfaces.beans.IStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/statements")
public class StatementController extends BaseCrudController<StatementDto> {

    private final IStatementService statementService;
    private final IAdminService adminService;

    @Autowired
    public StatementController(IStatementService statementService, IAdminService adminService) {
        this.statementService = statementService;
        this.adminService = adminService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody UserAndFacultyDto userAndFaculty) {
        StatementDto statement = adminService.registerStatement(userAndFaculty.getUserId(), userAndFaculty.getFacultyId());
        return new ResponseEntity<>(statement, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody StatementDto statement) {
        return updateCrud(statement);
    }

    protected IBaseCrudService<StatementDto> getService() {
        return statementService;
    }
}
