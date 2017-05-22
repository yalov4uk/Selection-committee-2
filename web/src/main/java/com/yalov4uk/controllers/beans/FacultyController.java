package com.yalov4uk.controllers.beans;

import com.yalov4uk.abstracts.BaseCrudController;
import com.yalov4uk.beans.Faculty;
import com.yalov4uk.beans.SubjectName;
import com.yalov4uk.dto.FacultyDto;
import com.yalov4uk.dto.services.FacultyAndSubjectNameDto;
import com.yalov4uk.exceptions.NotFoundException;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import com.yalov4uk.interfaces.beans.IFacultyService;
import com.yalov4uk.interfaces.beans.ISubjectNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by valera on 5/17/17.
 */
@RestController
@RequestMapping(value = "/faculties")
public class FacultyController extends BaseCrudController<Faculty, FacultyDto> {

    private final IFacultyService facultyService;
    private final ISubjectNameService subjectNameService;

    @Autowired
    public FacultyController(IFacultyService facultyService, ISubjectNameService subjectNameService) {
        this.facultyService = facultyService;
        this.subjectNameService = subjectNameService;
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

    @RequestMapping(value = "/addSubjectName", method = RequestMethod.POST)
    public ResponseEntity addSubjectName(@RequestBody FacultyAndSubjectNameDto facultyAndSubjectNameDto) {
        Faculty faculty = facultyService.read(facultyAndSubjectNameDto.getFaculty().getId());
        SubjectName subjectName = subjectNameService.read(facultyAndSubjectNameDto.getSubjectName().getId());
        if (faculty == null || subjectName == null || faculty.getRequiredSubjects().contains(subjectName)) {
            throw new NotFoundException();
        }
        facultyService.addSubjectName(faculty, subjectName);
        logger.info("added subject name to faculty");
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteSubjectName", method = RequestMethod.POST)
    public ResponseEntity deleteSubjectName(@RequestBody FacultyAndSubjectNameDto facultyAndSubjectNameDto) {
        Faculty faculty = facultyService.read(facultyAndSubjectNameDto.getFaculty().getId());
        SubjectName subjectName = subjectNameService.read(facultyAndSubjectNameDto.getSubjectName().getId());
        if (faculty == null || subjectName == null || !faculty.getRequiredSubjects().contains(subjectName)) {
            throw new NotFoundException();
        }
        facultyService.deleteSubjectName(faculty, subjectName);
        logger.info("deleted subject name from faculty");
        return new ResponseEntity(HttpStatus.OK);
    }

    protected IBaseCrudService<Faculty> getService() {
        return facultyService;
    }

    protected Class<Faculty> getBeanClass() {
        return Faculty.class;
    }

    protected Class<FacultyDto> getDtoClass() {
        return FacultyDto.class;
    }
}
