package com.yalov4uk.interfaces;

import com.yalov4uk.interfaces.abstracts.IBaseService;
import dto.StatementDto;

import java.util.List;

/**
 * Created by valera on 5/6/17.
 */
public interface IAdminService extends IBaseService {

    StatementDto registerStatement(Integer userId, Integer facultyId);

    List<StatementDto> calculateEntrants(Integer facultyId);
}
