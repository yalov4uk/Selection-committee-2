package com.yalov4uk.controllers;

import com.yalov4uk.abstracts.BaseCrudController;
import com.yalov4uk.dto.SubjectDto;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import com.yalov4uk.interfaces.beans.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/subjects")
public class SubjectController extends BaseCrudController<SubjectDto> {

    private final ISubjectService subjectService;

    @Autowired
    public SubjectController(ISubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody SubjectDto subject) {
        return createCrud(subject);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable Integer id, @RequestBody SubjectDto subject) {
        subject.setId(id);
        return updateCrud(subject);
    }

    protected IBaseCrudService<SubjectDto> getService() {
        return subjectService;
    }
}
