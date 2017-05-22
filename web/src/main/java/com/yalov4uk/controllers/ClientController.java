package com.yalov4uk.controllers;

import com.yalov4uk.abstracts.BaseController;
import com.yalov4uk.beans.User;
import com.yalov4uk.exceptions.NotFoundException;
import com.yalov4uk.interfaces.IClientService;
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
public class ClientController extends BaseController {

    @Autowired
    private IClientService clientService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody User user) {
        user = clientService.register(user);
        if (user == null) {
            throw new NotFoundException();
        }
        logger.info("user registered");
        logger.debug(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody User user) {
        user = clientService.login(user.getLogin(), user.getPassword());
        if (user == null) {
            throw new NotFoundException();
        }
        logger.info("user login");
        logger.debug(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
