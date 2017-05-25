package com.yalov4uk.abstracts;

import abstracts.Dto;
import com.yalov4uk.exceptions.NotFoundException;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by valera on 5/17/17.
 */
public abstract class BaseCrudController<D extends Dto> {

    protected abstract IBaseCrudService<D> getService();

    protected ResponseEntity<D> createCrud(D object) {
        getService().create(object);
        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }

    protected ResponseEntity<D> updateCrud(D object) {
        if (getService().update(object) == null) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable int id) {
        getService().delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<D> read(@PathVariable int id) {
        D object = getService().read(id);
        if (object == null) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<D>> getAll() {
        List<D> list = getService().getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
