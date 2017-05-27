package com.yalov4uk.controllers.beans;

import com.yalov4uk.abstracts.BaseCrudController;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import com.yalov4uk.interfaces.beans.IUserService;
import com.yalov4uk.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController extends BaseCrudController<UserDto> {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody UserDto user) {
        return createCrud(user);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody UserDto user) {
        return updateCrud(user);
    }

    protected IBaseCrudService<UserDto> getService() {
        return userService;
    }
}
