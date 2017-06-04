package com.yalov4uk.interfaces.beans;

import com.yalov4uk.dto.SubjectDto;
import com.yalov4uk.dto.UserDto;
import com.yalov4uk.dto.input.UserInputDto;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;

import java.util.List;

public interface IUserService extends IBaseCrudService<UserDto> {

    UserDto findByLogin(String login);

    void addSubject(Integer userId, Integer subjectId);

    void deleteSubject(Integer userId, Integer subjectId);

    List<SubjectDto> getSubjects(Integer userId);

    UserDto update(UserInputDto userInputDto);
}
