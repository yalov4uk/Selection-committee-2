package com.yalov4uk.core.controllers.beans;

import com.yalov4uk.beans.Subject;
import com.yalov4uk.core.controllers.abstracts.CrudController;
import com.yalov4uk.dto.SubjectDto;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import com.yalov4uk.interfaces.beans.ISubjectService;
import org.modelmapper.ModelMapper;
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
public class SubjectController extends CrudController<Subject> {

    private final ISubjectService subjectService;
    private final ModelMapper modelMapper;

    @Autowired
    public SubjectController(ISubjectService subjectService, ModelMapper modelMapper) {
        super(Subject.class);
        this.subjectService = subjectService;
        this.modelMapper = modelMapper;
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

    protected ModelMapper getMapper() {
        return modelMapper;
    }
}
