package com.yalov4uk.services.beans;

import com.yalov4uk.abstracts.BaseCrudService;
import com.yalov4uk.beans.Faculty;
import com.yalov4uk.interfaces.IBaseDao;
import com.yalov4uk.interfaces.IFacultyDao;
import com.yalov4uk.interfaces.beans.IFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by valera on 5/17/17.
 */
@Service
public class FacultyService extends BaseCrudService<Faculty> implements IFacultyService {

    private final IFacultyDao facultyDao;

    @Autowired
    public FacultyService(IFacultyDao facultyDao) {
        this.facultyDao = facultyDao;
    }

    protected IBaseDao<Faculty> getDao() {
        return facultyDao;
    }
}
