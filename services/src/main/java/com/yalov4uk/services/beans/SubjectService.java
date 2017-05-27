package com.yalov4uk.services.beans;

import com.yalov4uk.abstracts.BaseCrudService;
import com.yalov4uk.beans.Subject;
import com.yalov4uk.dto.SubjectDto;
import com.yalov4uk.interfaces.IBaseDao;
import com.yalov4uk.interfaces.ISubjectDao;
import com.yalov4uk.interfaces.ISubjectNameDao;
import com.yalov4uk.interfaces.IUserDao;
import com.yalov4uk.interfaces.beans.ISubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService extends BaseCrudService<Subject, SubjectDto> implements ISubjectService {

    private final ISubjectDao subjectDao;
    private final IUserDao userDao;
    private final ISubjectNameDao subjectNameDao;

    @Autowired
    public SubjectService(ModelMapper modelMapper, ISubjectDao subjectDao, IUserDao userDao, ISubjectNameDao subjectNameDao) {
        super(modelMapper);
        this.subjectDao = subjectDao;
        this.userDao = userDao;
        this.subjectNameDao = subjectNameDao;
    }

    @Override
    public SubjectDto create(SubjectDto subjectDto) {
        Subject subject = modelMapper.map(subjectDto, Subject.class);
        subjectDao.persist(subject);

        userDao.find(subject.getUser().getId()).getSubjects().add(subject);
        subjectNameDao.find(subject.getSubjectName().getId()).getSubjects().add(subject);

        return modelMapper.map(subject, SubjectDto.class);
    }

    @Override
    public void delete(Integer key) {
        Subject subject = subjectDao.find(key);
        subjectDao.delete(key);

        userDao.find(subject.getUser().getId()).getSubjects().remove(subject);
        subjectNameDao.find(subject.getSubjectName().getId()).getSubjects().remove(subject);
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
