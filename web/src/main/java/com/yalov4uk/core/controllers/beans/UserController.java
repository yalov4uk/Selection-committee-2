package com.yalov4uk.core.controllers.beans;

import com.yalov4uk.beans.User;
import com.yalov4uk.core.controllers.abstracts.CrudController;
import com.yalov4uk.dto.UserDto;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import com.yalov4uk.interfaces.beans.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by valera on 5/17/17.
 */
@RestController
@RequestMapping(value = "/users")
public class UserController extends CrudController<User> {

    private final IUserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(IUserService userService, ModelMapper modelMapper) {
        super(User.class);
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody UserDto dto) {
        return createCrud(dto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody UserDto dto) {
        return updateCrud(dto);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestBody UserDto dto) {
        return deleteCrud(dto);
    }

    protected IBaseCrudService<User> getService() {
        return userService;
    }

    protected ModelMapper getMapper() {
        return modelMapper;
    }
}
