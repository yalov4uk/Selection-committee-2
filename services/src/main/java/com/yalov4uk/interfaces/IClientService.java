package com.yalov4uk.interfaces;

import com.yalov4uk.interfaces.abstracts.IBaseService;
import dto.UserDto;

/**
 * Created by valera on 5/6/17.
 */
public interface IClientService extends IBaseService {

    UserDto register(UserDto user);
}
