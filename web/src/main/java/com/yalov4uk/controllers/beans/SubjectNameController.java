package com.yalov4uk.controllers.beans;

import com.yalov4uk.abstracts.BaseCrudController;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import com.yalov4uk.interfaces.beans.ISubjectNameService;
import com.yalov4uk.dto.SubjectNameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/subjectNames")
public class SubjectNameController extends BaseCrudController<SubjectNameDto> {

    private final ISubjectNameService subjectNameService;

    @Autowired
    public SubjectNameController(ISubjectNameService subjectNameService) {
        this.subjectNameService = subjectNameService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody SubjectNameDto subjectName) {
        return createCrud(subjectName);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody SubjectNameDto subjectName) {
        return updateCrud(subjectName);
    }

    protected IBaseCrudService<SubjectNameDto> getService() {
        return subjectNameService;
    }
}
