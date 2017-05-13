package com.yalov4uk.factories;

import com.yalov4uk.beans.*;
import com.yalov4uk.interfaces.*;
import com.yalov4uk.interfaces.factories.IDaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by valera on 5/6/17.
 */
@Component
public class DaoFactory implements IDaoFactory {

    private Map<Class, IBaseDao> map;

    private final IFacultyDao facultyDao;
    private final IRoleDao roleDao;
    private final IStatementDao statementDao;
    private final ISubjectDao subjectDao;
    private final ISubjectNameDao subjectNameDao;
    private final IUserDao userDao;

    @Autowired
    private DaoFactory(IFacultyDao facultyDao, IRoleDao roleDao, IStatementDao statementDao,
                       ISubjectDao subjectDao, ISubjectNameDao subjectNameDao, IUserDao userDao) {
        this.facultyDao = facultyDao;
        this.roleDao = roleDao;
        this.statementDao = statementDao;
        this.subjectDao = subjectDao;
        this.subjectNameDao = subjectNameDao;
        this.userDao = userDao;
        map = initializeMap();
    }

    public IBaseDao getDao(Class name) {
        if (name != null && map.containsKey(name)) {
            return map.get(name);
        }
        return null;
    }

    private Map<Class, IBaseDao> initializeMap() {
        Map<Class, IBaseDao> map = new HashMap<>();
        map.put(Faculty.class, facultyDao);
        map.put(Role.class, roleDao);
        map.put(Statement.class, statementDao);
        map.put(Subject.class, subjectDao);
        map.put(SubjectName.class, subjectNameDao);
        map.put(User.class, userDao);
        return map;
    }
}
