package com.yalov4uk.abstracts;

import com.yalov4uk.exceptions.NotFoundException;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by valera on 5/17/17.
 */
public abstract class BaseCrudController<T extends Bean, D extends Dto> extends BaseController {

    protected abstract IBaseCrudService<T> getService();

    protected abstract Class<T> getBeanClass();

    protected abstract Class<D> getDtoClass();

    protected ResponseEntity<D> createCrud(Dto dto) {
        T bean = modelMapper.map(dto, getBeanClass());
        getService().create(bean);
        logger.info("created");
        logger.debug(bean);
        return new ResponseEntity<>(modelMapper.map(bean, getDtoClass()), HttpStatus.OK);
    }

    protected ResponseEntity<D> updateCrud(Dto dto) {
        T bean = modelMapper.map(dto, getBeanClass());
        if (getService().update(bean) == null) {
            throw new NotFoundException();
        }
        logger.info("updated");
        logger.debug(bean);
        return new ResponseEntity<>(modelMapper.map(bean, getDtoClass()), HttpStatus.OK);
    }

    protected ResponseEntity deleteCrud(Dto dto) {
        T bean = modelMapper.map(dto, getBeanClass());
        getService().delete(bean);
        logger.info("deleted");
        logger.debug(bean);
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<D> read(@PathVariable String id) {
        T bean = getService().read(Integer.parseInt(id));
        if (bean == null) {
            throw new NotFoundException();
        }
        D dto = modelMapper.map(bean, getDtoClass());
        logger.info("read");
        logger.debug(dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<D>> getAll() {
        List<D> list = getService().getAll()
                .stream()
                .map(object -> modelMapper.map(object, getDtoClass()))
                .collect(Collectors.toList());
        logger.info("got all");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
