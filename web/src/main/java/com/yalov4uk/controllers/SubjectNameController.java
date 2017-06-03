package com.yalov4uk.controllers;

import com.yalov4uk.abstracts.BaseCrudController;
import com.yalov4uk.dto.SubjectNameDto;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import com.yalov4uk.interfaces.beans.ISubjectNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/subjectNames")
public class SubjectNameController extends BaseCrudController<SubjectNameDto> {

    private final ISubjectNameService subjectNameService;

    @Autowired
    public SubjectNameController(ISubjectNameService subjectNameService) {
        this.subjectNameService = subjectNameService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody SubjectNameDto subjectName) {
        return createCrud(subjectName);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable Integer id, @RequestBody SubjectNameDto subjectName) {
        subjectName.setId(id);
        return updateCrud(subjectName);
    }

    protected IBaseCrudService<SubjectNameDto> getService() {
        return subjectNameService;
    }
}
