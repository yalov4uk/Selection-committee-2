package com.yalov4uk.core.controllers.beans;

import com.yalov4uk.beans.Faculty;
import com.yalov4uk.core.controllers.abstracts.CrudController;
import com.yalov4uk.dto.FacultyDto;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import com.yalov4uk.interfaces.beans.IFacultyService;
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
@RequestMapping(value = "/faculties")
public class FacultyController extends CrudController<Faculty> {

    private final IFacultyService facultyService;
    private final ModelMapper modelMapper;

    @Autowired
    public FacultyController(IFacultyService facultyService, ModelMapper modelMapper) {
        super(Faculty.class);
        this.facultyService = facultyService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody FacultyDto dto) {
        return createCrud(dto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody FacultyDto dto) {
        return updateCrud(dto);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestBody FacultyDto dto) {
        return deleteCrud(dto);
    }

    protected IBaseCrudService<Faculty> getService() {
        return facultyService;
    }

    protected ModelMapper getMapper() {
        return modelMapper;
    }
}
