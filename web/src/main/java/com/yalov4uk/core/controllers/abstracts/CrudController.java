package com.yalov4uk.core.controllers.abstracts;

import com.yalov4uk.abstracts.Bean;
import com.yalov4uk.abstracts.Dto;
import com.yalov4uk.core.interfaces.IDtoFactory;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.stream.Collectors;

/**
 * Created by valera on 5/17/17.
 */
public abstract class CrudController<T extends Bean> {

    @Autowired
    private IDtoFactory dtoFactory;
    private Class<T> clazz;

    protected abstract IBaseCrudService<T> getService();

    protected abstract ModelMapper getMapper();


    protected ResponseEntity createCrud(Dto dto) {
        try {
            T bean = getMapper().map(dto, clazz);
            getService().create(bean);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    protected ResponseEntity updateCrud(Dto dto) {
        try {
            T bean = getMapper().map(dto, clazz);
            if (getService().update(bean) == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    protected ResponseEntity deleteCrud(Dto dto) {
        try {
            T bean = getMapper().map(dto, clazz);
            getService().delete(bean);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity read(@PathVariable String id) {
        try {
            T bean = getService().read(Integer.parseInt(id));
            if (bean == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            Class dtoClass = dtoFactory.getDto(bean.getClass());
            Dto dto = (Dto) getMapper().map(bean, dtoClass);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAll() {
        return new ResponseEntity<>(getService().getAll()
                .stream()
                .map(object -> {
                    Class dtoClass = dtoFactory.getDto(object.getClass());
                    return (Dto) getMapper().map(object, dtoClass);
                })
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    public CrudController(Class<T> clazz) {
        this.clazz = clazz;
    }
}
