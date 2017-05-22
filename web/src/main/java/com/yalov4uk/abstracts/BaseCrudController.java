package com.yalov4uk.abstracts;

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
public abstract class BaseCrudController<T extends Bean> extends BaseController {

    protected abstract IBaseCrudService<T> getService();

    protected ResponseEntity<T> createCrud(T object) {
        getService().create(object);
        logger.info("created");
        logger.debug(object);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    protected ResponseEntity<T> updateCrud(T object) {
        if (getService().update(object) == null) {
            throw new NotFoundException();
        }
        logger.info("updated");
        logger.debug(object);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable String id) {
        getService().delete(Integer.parseInt(id));
        logger.info("deleted");
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<T> read(@PathVariable String id) {
        T object = getService().read(Integer.parseInt(id));
        if (object == null) {
            throw new NotFoundException();
        }
        logger.info("read");
        logger.debug(object);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<T>> getAll() {
        List<T> list = getService().getAll();
        logger.info("got all");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
