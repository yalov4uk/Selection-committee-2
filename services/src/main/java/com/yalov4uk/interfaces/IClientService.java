package com.yalov4uk.interfaces;

import com.yalov4uk.beans.User;
import com.yalov4uk.interfaces.abstracts.IBaseService;

/**
 * Created by valera on 5/6/17.
 */
public interface IClientService extends IBaseService {

    User register(String name, String login, String password);

    User login(String login, String password);
}
