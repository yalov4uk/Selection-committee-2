package com.yalov4uk.interfaces;

import com.yalov4uk.dto.UserDto;
import com.yalov4uk.interfaces.abstracts.IBaseService;

public interface IClientService extends IBaseService {

    UserDto register(UserDto user);

    void login(UserDto user);
}
