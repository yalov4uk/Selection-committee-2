package com.yalov4uk.interfaces.beans;

import com.yalov4uk.beans.User;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import com.yalov4uk.dto.SubjectDto;
import com.yalov4uk.dto.UserDto;

public interface IUserService extends IBaseCrudService<UserDto> {

    User findByLogin(String login);

    void addSubject(UserDto userDto, SubjectDto subjectDto);

    void deleteSubject(UserDto userDto, SubjectDto subjectDto);
}
