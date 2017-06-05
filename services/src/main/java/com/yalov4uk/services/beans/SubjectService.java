package com.yalov4uk.services.beans;

import com.yalov4uk.abstracts.BaseCrudService;
import com.yalov4uk.beans.Subject;
import com.yalov4uk.beans.SubjectName;
import com.yalov4uk.beans.User;
import com.yalov4uk.dto.SubjectDto;
import com.yalov4uk.dto.input.SubjectInputDto;
import com.yalov4uk.exceptions.ServiceUncheckedException;
import com.yalov4uk.interfaces.IBaseDao;
import com.yalov4uk.interfaces.ISubjectDao;
import com.yalov4uk.interfaces.ISubjectNameDao;
import com.yalov4uk.interfaces.IUserDao;
import com.yalov4uk.interfaces.beans.ISubjectService;
import com.yalov4uk.interfaces.validators.DtoValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService extends BaseCrudService<Subject, SubjectDto> implements ISubjectService {

    private final ISubjectDao subjectDao;
    private final IUserDao userDao;
    private final ISubjectNameDao subjectNameDao;
    private final DtoValidator<SubjectDto> subjectValidator;

    @Autowired
    public SubjectService(ModelMapper modelMapper, ISubjectDao subjectDao, IUserDao userDao,
                          ISubjectNameDao subjectNameDao, DtoValidator<SubjectDto> subjectValidator) {
        super(modelMapper);
        this.subjectDao = subjectDao;
        this.userDao = userDao;
        this.subjectNameDao = subjectNameDao;
        this.subjectValidator = subjectValidator;
    }

    @Override
    public SubjectDto create(SubjectDto subjectDto) {
        subjectValidator.validate(subjectDto);
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

    @Override
    public SubjectDto create(SubjectInputDto subjectInputDto) {
        if (subjectInputDto == null || subjectInputDto.getValue() == null ||
                subjectInputDto.getSubjectNameId() == null || subjectInputDto.getUserId() == null ||
                subjectInputDto.getValue() > 100 || subjectInputDto.getValue() < 0) {
            throw new ServiceUncheckedException("wrong input");
        }

        SubjectName subjectName = subjectNameDao.find(subjectInputDto.getSubjectNameId());
        User user = userDao.find(subjectInputDto.getUserId());
        if (subjectName == null || user == null) {
            throw new ServiceUncheckedException("subjectName or user not found");
        }
        Subject subject = new Subject(subjectInputDto.getValue());
        subject.setSubjectName(subjectName);
        subject.setUser(user);
        subjectDao.persist(subject);

        userDao.find(subject.getUser().getId()).getSubjects().add(subject);
        subjectNameDao.find(subject.getSubjectName().getId()).getSubjects().add(subject);
        return modelMapper.map(subject, SubjectDto.class);
    }

    @Override
    public SubjectDto update(SubjectInputDto subjectInputDto) {
        if (subjectInputDto == null || subjectInputDto.getId() == null || subjectInputDto.getValue() == null ||
                subjectInputDto.getSubjectNameId() == null || subjectInputDto.getUserId() == null ||
                subjectInputDto.getValue() > 100 || subjectInputDto.getValue() < 0) {
            throw new ServiceUncheckedException("wrong input");
        }

        Subject subject = subjectDao.find(subjectInputDto.getId());
        SubjectName subjectName = subjectNameDao.find(subjectInputDto.getSubjectNameId());
        User user = userDao.find(subjectInputDto.getUserId());
        if (subject == null || subjectName == null || user == null) {
            throw new ServiceUncheckedException("subject or subjectName or user not found");
        }
        userDao.find(subject.getUser().getId()).getSubjects().remove(subject);
        subjectNameDao.find(subject.getSubjectName().getId()).getSubjects().remove(subject);

        subject.setSubjectName(subjectName);
        subject.setUser(user);
        subjectDao.persist(subject);

        user.getSubjects().add(subject);
        subjectName.getSubjects().add(subject);
        return modelMapper.map(subject, SubjectDto.class);
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

    protected DtoValidator<SubjectDto> getValidator() {
        return subjectValidator;
    }
}
