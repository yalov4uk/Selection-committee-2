package com.yalov4uk.abstracts;

import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public abstract class BaseCrudController<D extends Dto> {

    protected abstract IBaseCrudService<D> getService();

    protected ResponseEntity<D> create(D object) {
        object = getService().create(object);
        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }

    protected ResponseEntity<D> update(D object) {
        object = getService().update(object);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Integer id) {
        getService().delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<D> read(@PathVariable Integer id) {
        D object = getService().read(id);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<D>> getAll() {
        List<D> list = getService().getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
