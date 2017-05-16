package com.yalov4uk.core.controllers;

import com.yalov4uk.abstracts.Bean;
import com.yalov4uk.beans.Role;
import com.yalov4uk.core.interfaces.IEntityFactory;
import com.yalov4uk.interfaces.ICRUDService;


import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by valera on 5/10/17.
 */
@RestController
@RequestMapping(value = "/crud")
public class CRUDController {

    private final ICRUDService crudService;
    private final IEntityFactory entityFactory;

    @Autowired
    public CRUDController(ICRUDService crudService, IEntityFactory entityFactory) {
        this.crudService = crudService;
        this.entityFactory = entityFactory;
    }

    /*@RequestMapping(value = "/{entityName}/create", method = RequestMethod.POST)
    public <T extends Bean> void create(@PathVariable String entityName, @RequestBody Object object) {
        Class entityClass = entityFactory.getEntity(entityName);
        if (entityClass == null) return;
        //crudService.create(object);
    }*/

    @RequestMapping(value = "/{entityName}/{id}", method = RequestMethod.GET)
    public ResponseEntity read(@PathVariable String entityName, @PathVariable String id) {
        Class entityClass = entityFactory.getEntity(entityName);
        if (entityClass == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        try {
            Bean object = crudService.read(Integer.parseInt(id), entityClass);
            if (object == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(object, HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{entityName}", method = RequestMethod.GET)
    public ResponseEntity getAll(@PathVariable String entityName) {
        Class entityClass = entityFactory.getEntity(entityName);
        if (entityClass == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(crudService.getAll(entityClass), HttpStatus.OK);
    }
}
