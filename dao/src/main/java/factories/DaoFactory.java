package factories;

import beans.*;
import factories.creators.*;
import interfaces.IBaseDao;
import interfaces.factories.IDaoCreator;
import interfaces.factories.IDaoFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by valera on 5/6/17.
 */
public class DaoFactory implements IDaoFactory{

    private Map<Class, IDaoCreator> map;

    private DaoFactory() {
        map = initializeMap();
    }

    public IBaseDao getDao(Class name){
        if (name != null && map.containsKey(name)) {
            return map.get(name).create();
        }
        return null;
    }

    private static DaoFactory ourInstance;

    public static DaoFactory getInstance() {
        if (ourInstance == null) {
            ourInstance = new DaoFactory();
        }
        return ourInstance;
    }

    private Map<Class,IDaoCreator> initializeMap() {
        Map<Class, IDaoCreator> map = new HashMap<>();
        map.put(Faculty.class, new FacultyDaoCreator());
        map.put(Role.class, new RoleDaoCreator());
        map.put(Statement.class, new StatementDaoCreator());
        map.put(Subject.class, new SubjectDaoCreator());
        map.put(SubjectName.class, new SubjectNameDaoCreator());
        map.put(User.class, new UserDaoCreator());
        return map;
    }
}
