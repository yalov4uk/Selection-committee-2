package com.yalov4uk.abstracts;

import com.yalov4uk.exceptions.ServiceUncheckedException;
import com.yalov4uk.interfaces.IBaseDao;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseCrudService<T extends Bean, D extends Dto> extends BaseService implements IBaseCrudService<D> {

    protected abstract IBaseDao<T> getDao();

    protected abstract Class<T> getBeanClass();

    protected abstract Class<D> getDtoClass();

    protected abstract BaseDtoValidator<D> getValidator();

    public D create(D dto) {
        validate(dto);
        T object = modelMapper.map(dto, getBeanClass());
        IBaseDao<T> dao = getDao();
        dao.persist(object);
        return modelMapper.map(object, getDtoClass());
    }

    public D read(Integer key) {
        IBaseDao<T> dao = getDao();
        T object = dao.find(key);
        if (object == null) {
            throw new ServiceUncheckedException("object not found");
        }
        return modelMapper.map(object, getDtoClass());
    }

    public D update(D dto) {
        validate(dto);
        T object = modelMapper.map(dto, getBeanClass());
        IBaseDao<T> dao = getDao();
        return modelMapper.map(dao.update(object), getDtoClass());
    }

    public void delete(Integer key) {
        IBaseDao<T> dao = getDao();
        dao.delete(key);
    }

    public List<D> getAll() {
        IBaseDao<T> dao = getDao();
        return dao.getAll()
                .stream()
                .map(object -> modelMapper.map(object, getDtoClass()))
                .collect(Collectors.toList());
    }

    private void validate(D dto) {
        getValidator().validate(dto);
    }

    public BaseCrudService(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
