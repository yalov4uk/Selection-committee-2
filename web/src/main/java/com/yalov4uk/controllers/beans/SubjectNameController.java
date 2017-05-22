package com.yalov4uk.controllers.beans;

import com.yalov4uk.abstracts.BaseCrudController;
import com.yalov4uk.beans.SubjectName;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import com.yalov4uk.interfaces.beans.ISubjectNameService;
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
@RequestMapping(value = "/subjectNames")
public class SubjectNameController extends BaseCrudController<SubjectName> {

    private final ISubjectNameService subjectNameService;

    @Autowired
    public SubjectNameController(ISubjectNameService subjectNameService) {
        this.subjectNameService = subjectNameService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody SubjectName subjectName) {
        return createCrud(subjectName);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody SubjectName subjectName) {
        return updateCrud(subjectName);
    }

    protected IBaseCrudService<SubjectName> getService() {
        return subjectNameService;
    }
}
