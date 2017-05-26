package com.yalov4uk.interfaces.beans;

import com.yalov4uk.beans.User;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import dto.SubjectDto;
import dto.UserDto;

/**
 * Created by valera on 5/17/17.
 */
public interface IUserService extends IBaseCrudService<UserDto> {

    User findByLogin(String login);

    void addSubject(UserDto userDto, SubjectDto subjectDto);

    void deleteSubject(UserDto userDto, SubjectDto subjectDto);
}
