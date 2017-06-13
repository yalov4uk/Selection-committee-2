package com.yalov4uk.controllers;

import com.yalov4uk.abstracts.BaseCrudController;
import com.yalov4uk.dto.SubjectDto;
import com.yalov4uk.dto.UserDto;
import com.yalov4uk.dto.input.UserInputDto;
import com.yalov4uk.interfaces.IClientService;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import com.yalov4uk.interfaces.beans.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController extends BaseCrudController<UserDto> {

    private final IUserService userService;
    private final IClientService clientService;

    @Autowired
    public UserController(IUserService userService, IClientService clientService) {
        this.userService = userService;
        this.clientService = clientService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDto> create(@RequestBody UserDto user) {
        user = clientService.register(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable Integer id, @RequestBody UserInputDto user) {
        user.setId(id);
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/subjects", method = RequestMethod.GET)
    public ResponseEntity getSubjects(@PathVariable Integer userId) {
        List<SubjectDto> subjects = userService.getSubjects(userId);
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    protected IBaseCrudService<UserDto> getService() {
        return userService;
    }
}
