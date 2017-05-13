package com.yalov4uk.core.controllers;

import com.yalov4uk.beans.Role;
import com.yalov4uk.interfaces.ICRUDService;
import com.yalov4uk.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by valera on 5/10/17.
 */
@RestController
public class CRUDController {

    @Autowired
    private ICRUDService crudService;

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public List<Role> getAllRoles() {
        return crudService.getAll(Role.class);
    }
}
