package com.yalov4uk.controllers.beans;

import com.yalov4uk.abstracts.BaseCrudController;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import com.yalov4uk.interfaces.beans.ISubjectService;
import com.yalov4uk.dto.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/subjects")
public class SubjectController extends BaseCrudController<SubjectDto> {

    private final ISubjectService subjectService;

    @Autowired
    public SubjectController(ISubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody SubjectDto subject) {
        return createCrud(subject);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody SubjectDto subject) {
        return updateCrud(subject);
    }

    protected IBaseCrudService<SubjectDto> getService() {
        return subjectService;
    }
}
