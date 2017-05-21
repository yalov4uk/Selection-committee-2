package com.yalov4uk.controllers;

import com.yalov4uk.beans.User;
import com.yalov4uk.controllers.abstracts.BaseController;
import com.yalov4uk.dto.UserDto;
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
    public ResponseEntity register(@RequestBody UserDto userDto) {
        try {
            User user = clientService.register(userDto.getName(), userDto.getLogin(), userDto.getPassword());
            if (user == null) {
                logger.error("login already in use");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            logger.info("user registered");
            logger.debug(user);
            return new ResponseEntity<>(modelMapper.map(user, UserDto.class), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("user not registered");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody UserDto userDto) {
        try {
            User user = clientService.login(userDto.getLogin(), userDto.getPassword());
            if (user == null) {
                logger.error("invalid login or password");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            logger.info("user login");
            logger.debug(user);
            return new ResponseEntity<>(modelMapper.map(user, UserDto.class), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("user not login");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
