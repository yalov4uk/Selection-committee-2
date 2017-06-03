package com.yalov4uk.controllers;

import com.yalov4uk.dto.UserDto;
import com.yalov4uk.interfaces.IClientService;
import com.yalov4uk.interfaces.beans.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final IClientService clientService;
    private final IUserService userService;

    @Autowired
    public AuthController(IClientService clientService, IUserService userService) {
        this.clientService = clientService;
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody UserDto user) {
        user = clientService.register(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody UserDto user) {
        clientService.login(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
