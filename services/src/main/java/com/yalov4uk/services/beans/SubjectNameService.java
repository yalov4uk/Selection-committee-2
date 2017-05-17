package com.yalov4uk.services.beans;

import com.yalov4uk.abstracts.BaseCrudService;
import com.yalov4uk.beans.SubjectName;
import com.yalov4uk.interfaces.IBaseDao;
import com.yalov4uk.interfaces.ISubjectNameDao;
import com.yalov4uk.interfaces.beans.ISubjectNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by valera on 5/17/17.
 */
@Service
public class SubjectNameService extends BaseCrudService<SubjectName> implements ISubjectNameService {

    private final ISubjectNameDao subjectNameDao;

    @Autowired
    public SubjectNameService(ISubjectNameDao subjectNameDao) {
        this.subjectNameDao = subjectNameDao;
    }

    protected IBaseDao<SubjectName> getDao() {
        return subjectNameDao;
    }
}
