package com.yalov4uk.services.beans;

import com.yalov4uk.abstracts.BaseCrudService;
import com.yalov4uk.beans.Subject;
import com.yalov4uk.interfaces.IBaseDao;
import com.yalov4uk.interfaces.ISubjectDao;
import com.yalov4uk.interfaces.beans.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by valera on 5/17/17.
 */
@Service
public class SubjectService extends BaseCrudService<Subject> implements ISubjectService {

    private final ISubjectDao subjectDao;

    @Autowired
    public SubjectService(ISubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    protected IBaseDao<Subject> getDao() {
        return subjectDao;
    }
}