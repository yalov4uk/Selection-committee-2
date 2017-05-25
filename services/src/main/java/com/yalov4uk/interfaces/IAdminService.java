package com.yalov4uk.interfaces;

import com.yalov4uk.interfaces.abstracts.IBaseService;
import dto.FacultyDto;
import dto.StatementDto;
import dto.UserDto;

import java.util.List;

/**
 * Created by valera on 5/6/17.
 */
public interface IAdminService extends IBaseService {

    StatementDto registerStatement(UserDto user, FacultyDto faculty);

    List<StatementDto> calculateEntrants(Integer facultyId);
}
