package tools;

import abstracts.BaseService;
import abstracts.Bean;
import factories.DaoFactory;
import interfaces.IBaseDao;
import interfaces.ICRUDService;
import interfaces.factories.IDaoFactory;

import java.util.List;

/**
 * Created by valera on 5/6/17.
 */
public class CRUDService extends BaseService implements ICRUDService {

    private IDaoFactory daoFactory;

    public <T extends Bean> T create(T object) {
        return baseCommand(() -> {
            IBaseDao<T> dao = daoFactory.getDao(object.getClass());
            dao.persist(object);
            return object;
        });
    }

    public <T extends Bean> T read(Integer key, Class<T> name) {
        return baseCommand(() -> {
            IBaseDao<T> dao = daoFactory.getDao(name);
            return dao.find(key);
        });
    }

    public <T extends Bean> T update(T object) {
        return baseCommand(() -> {
            IBaseDao<T> dao = daoFactory.getDao(object.getClass());
            dao.update(object);
            return object;
        });
    }

    public <T extends Bean> T delete(T object) {
        return baseCommand(() -> {
            IBaseDao<T> dao = daoFactory.getDao(object.getClass());
            dao.delete(object.getId());
            return object;
        });
    }

    public <T extends Bean> List<T> getAll(Class<T> name) {
        return baseCommand(() -> {
            IBaseDao<T> dao = daoFactory.getDao(name);
            return dao.getAll();
        });
    }

    public CRUDService() {
        super();
        daoFactory = DaoFactory.getInstance();
    }
}
