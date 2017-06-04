package com.yalov4uk.interfaces.beans;

import com.yalov4uk.dto.StatementDto;
import com.yalov4uk.dto.input.StatementInputDto;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;

public interface IStatementService extends IBaseCrudService<StatementDto> {

    StatementDto update(StatementInputDto dto);
}
