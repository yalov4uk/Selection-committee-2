package com.yalov4uk.controllers.abstracts;

import com.yalov4uk.abstracts.Bean;
import com.yalov4uk.abstracts.Dto;
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

    protected ResponseEntity createCrud(Dto dto) {
        try {
            T bean = modelMapper.map(dto, getBeanClass());
            getService().create(bean);
            logger.info("created");
            logger.debug(bean);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("not created");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    protected ResponseEntity updateCrud(Dto dto) {
        try {
            T bean = modelMapper.map(dto, getBeanClass());
            if (getService().update(bean) == null) {
                logger.error("not found bean for update");
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            logger.info("updated");
            logger.debug(bean);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("not updated");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    protected ResponseEntity deleteCrud(Dto dto) {
        try {
            T bean = modelMapper.map(dto, getBeanClass());
            getService().delete(bean);
            logger.info("deleted");
            logger.debug(bean);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("not deleted");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity read(@PathVariable String id) {
        try {
            T bean = getService().read(Integer.parseInt(id));
            if (bean == null) {
                logger.error("not found bean for read");
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            D dto = modelMapper.map(bean, getDtoClass());
            logger.info("read");
            logger.debug(dto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("not read");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAll() {
        try {
            List<D> list = getService().getAll()
                    .stream()
                    .map(object -> modelMapper.map(object, getDtoClass()))
                    .collect(Collectors.toList());
            logger.info("got all");
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("not got all");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
