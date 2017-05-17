package com.yalov4uk.core.controllers.beans;

import com.yalov4uk.beans.SubjectName;
import com.yalov4uk.core.controllers.abstracts.CrudController;
import com.yalov4uk.dto.SubjectNameDto;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import com.yalov4uk.interfaces.beans.ISubjectNameService;
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
@RequestMapping(value = "/subjectNames")
public class SubjectNameController extends CrudController<SubjectName> {

    private final ISubjectNameService subjectNameService;
    private final ModelMapper modelMapper;

    @Autowired
    public SubjectNameController(ISubjectNameService subjectNameService, ModelMapper modelMapper) {
        super(SubjectName.class);
        this.subjectNameService = subjectNameService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody SubjectNameDto dto) {
        return createCrud(dto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody SubjectNameDto dto) {
        return updateCrud(dto);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestBody SubjectNameDto dto) {
        return deleteCrud(dto);
    }

    protected IBaseCrudService<SubjectName> getService() {
        return subjectNameService;
    }

    protected ModelMapper getMapper() {
        return modelMapper;
    }
}
