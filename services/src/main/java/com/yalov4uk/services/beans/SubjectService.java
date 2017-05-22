package com.yalov4uk.services.beans;

import com.yalov4uk.abstracts.BaseCrudService;
import com.yalov4uk.beans.Subject;
import com.yalov4uk.exceptions.ServiceUncheckedException;
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

    @Override
    public void create(Subject subject) {
        try {
            subjectDao.persist(subject);

            subject.getUser().getSubjects().add(subject);
            subject.getSubjectName().getSubjects().add(subject);

            logger.info("persisted");
        } catch (Exception e) {
            logger.error("not persisted");
            throw new ServiceUncheckedException(e);
        }
    }

    @Override
    public void delete(Subject subject) {
        try {
            subjectDao.delete(subject.getId());

            subject.getUser().getSubjects().remove(subject);
            subject.getSubjectName().getSubjects().remove(subject);

            logger.info("deleted");
        } catch (Exception e) {
            logger.error("not deleted");
            throw new ServiceUncheckedException(e);
        }
    }
}
