package com.yalov4uk.services.beans;

import com.yalov4uk.abstracts.BaseCrudService;
import com.yalov4uk.beans.Subject;
import com.yalov4uk.exceptions.ServiceUncheckedException;
import com.yalov4uk.interfaces.IBaseDao;
import com.yalov4uk.interfaces.ISubjectDao;
import com.yalov4uk.interfaces.ISubjectNameDao;
import com.yalov4uk.interfaces.IUserDao;
import com.yalov4uk.interfaces.beans.ISubjectService;
import dto.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by valera on 5/17/17.
 */
@Service
public class SubjectService extends BaseCrudService<Subject, SubjectDto> implements ISubjectService {

    @Autowired
    private ISubjectDao subjectDao;
    @Autowired
    private IUserDao userDao;
    @Autowired
    private ISubjectNameDao subjectNameDao;

    @Override
    public SubjectDto create(SubjectDto subjectDto) {
        try {
            Subject subject = modelMapper.map(subjectDto, Subject.class);
            subjectDao.persist(subject);

            userDao.find(subject.getUser().getId()).getSubjects().add(subject);
            subjectNameDao.find(subject.getSubjectName().getId()).getSubjects().add(subject);

            return modelMapper.map(subject, SubjectDto.class);
        } catch (Exception e) {
            throw new ServiceUncheckedException(e);
        }
    }

    @Override
    public SubjectDto delete(Integer key) {
        try {
            Subject subject = subjectDao.find(key);
            subjectDao.delete(key);

            userDao.find(subject.getUser().getId()).getSubjects().remove(subject);
            subjectNameDao.find(subject.getSubjectName().getId()).getSubjects().remove(subject);
            return null;
        } catch (Exception e) {
            throw new ServiceUncheckedException(e);
        }
    }

    protected IBaseDao<Subject> getDao() {
        return subjectDao;
    }


    protected Class<Subject> getBeanClass() {
        return Subject.class;
    }

    protected Class<SubjectDto> getDtoClass() {
        return SubjectDto.class;
    }
}
