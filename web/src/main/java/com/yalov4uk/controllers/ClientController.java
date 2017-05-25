package com.yalov4uk.controllers;

import com.yalov4uk.interfaces.IClientService;
import dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by valera on 5/21/17.
 */
@RestController
@RequestMapping(value = "/client")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody UserDto user) {
        user = clientService.register(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody UserDto user) {
        user = clientService.login(user.getLogin(), user.getPassword());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
