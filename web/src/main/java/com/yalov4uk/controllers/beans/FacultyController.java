package com.yalov4uk.controllers.beans;

import com.yalov4uk.abstracts.BaseCrudController;
import com.yalov4uk.beans.Faculty;
import com.yalov4uk.beans.SubjectName;
import com.yalov4uk.dto.services.FacultyAndSubjectName;
import com.yalov4uk.exceptions.NotFoundException;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import com.yalov4uk.interfaces.beans.IFacultyService;
import com.yalov4uk.interfaces.beans.ISubjectNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class FacultyController extends BaseCrudController<Faculty> {

    private final IFacultyService facultyService;
    private final ISubjectNameService subjectNameService;

    @Autowired
    public FacultyController(IFacultyService facultyService, ISubjectNameService subjectNameService) {
        this.facultyService = facultyService;
        this.subjectNameService = subjectNameService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Faculty faculty) {
        return createCrud(faculty);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody Faculty faculty) {
        return updateCrud(faculty);
    }

    @RequestMapping(value = "/addSubjectName", method = RequestMethod.POST)
    public ResponseEntity addSubjectName(@RequestBody FacultyAndSubjectName facultyAndSubjectName) {
        Faculty faculty = facultyService.read(facultyAndSubjectName.getFaculty().getId());
        SubjectName subjectName = subjectNameService.read(facultyAndSubjectName.getSubjectName().getId());
        if (faculty == null || subjectName == null || faculty.getRequiredSubjects().contains(subjectName)) {
            throw new NotFoundException();
        }
        facultyService.addSubjectName(faculty, subjectName);
        logger.info("added subject name to faculty");
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteSubjectName", method = RequestMethod.POST)
    public ResponseEntity deleteSubjectName(@RequestBody FacultyAndSubjectName facultyAndSubjectName) {
        Faculty faculty = facultyService.read(facultyAndSubjectName.getFaculty().getId());
        SubjectName subjectName = subjectNameService.read(facultyAndSubjectName.getSubjectName().getId());
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
}
