package com.yalov4uk.services.beans;

import com.yalov4uk.abstracts.BaseCrudService;
import com.yalov4uk.beans.SubjectName;
import com.yalov4uk.dto.SubjectNameDto;
import com.yalov4uk.interfaces.IBaseDao;
import com.yalov4uk.interfaces.ISubjectNameDao;
import com.yalov4uk.interfaces.beans.ISubjectNameService;
import com.yalov4uk.interfaces.validators.DtoValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectNameService extends BaseCrudService<SubjectName, SubjectNameDto> implements ISubjectNameService {

    private final ISubjectNameDao subjectNameDao;

    private final DtoValidator<SubjectNameDto> subjectNameValidator;

    @Autowired
    public SubjectNameService(ModelMapper modelMapper, ISubjectNameDao subjectNameDao,
                              DtoValidator<SubjectNameDto> subjectNameValidator) {
        super(modelMapper);
        this.subjectNameDao = subjectNameDao;
        this.subjectNameValidator = subjectNameValidator;
    }

    protected IBaseDao<SubjectName> getDao() {
        return subjectNameDao;
    }

    protected Class<SubjectName> getBeanClass() {
        return SubjectName.class;
    }

    protected Class<SubjectNameDto> getDtoClass() {
        return SubjectNameDto.class;
    }

    protected DtoValidator<SubjectNameDto> getValidator() {
        return subjectNameValidator;
    }
}
