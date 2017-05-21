package com.yalov4uk.controllers.beans;

import com.yalov4uk.beans.Subject;
import com.yalov4uk.controllers.abstracts.BaseCrudController;
import com.yalov4uk.dto.SubjectDto;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import com.yalov4uk.interfaces.beans.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by valera on 5/17/17.
 */
@RestController
@RequestMapping(value = "/subjects")
public class SubjectController extends BaseCrudController<Subject, SubjectDto> {

    private final ISubjectService subjectService;

    @Autowired
    public SubjectController(ISubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody SubjectDto dto) {
        return createCrud(dto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody SubjectDto dto) {
        return updateCrud(dto);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestBody SubjectDto dto) {
        return deleteCrud(dto);
    }

    protected IBaseCrudService<Subject> getService() {
        return subjectService;
    }

    protected Class<Subject> getBeanClass() {
        return Subject.class;
    }

    protected Class<SubjectDto> getDtoClass() {
        return SubjectDto.class;
    }
}
