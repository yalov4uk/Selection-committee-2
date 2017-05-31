package com.yalov4uk.controllers.beans;

import com.yalov4uk.abstracts.BaseCrudController;
import com.yalov4uk.dto.FacultyDto;
import com.yalov4uk.dto.SubjectNameDto;
import com.yalov4uk.dto.UserDto;
import com.yalov4uk.interfaces.IEnrolleeService;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import com.yalov4uk.interfaces.beans.IFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/faculties")
public class FacultyController extends BaseCrudController<FacultyDto> {

    private final IFacultyService facultyService;
    private final IEnrolleeService enrolleeService;

    @Autowired
    public FacultyController(IFacultyService facultyService, IEnrolleeService enrolleeService) {
        this.facultyService = facultyService;
        this.enrolleeService = enrolleeService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<FacultyDto> create(@RequestBody FacultyDto faculty) {
        return createCrud(faculty);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<FacultyDto> update(@RequestBody FacultyDto faculty) {
        return updateCrud(faculty);
    }

    @RequestMapping(value = "/{facultyId}/subjects", method = RequestMethod.GET)
    public ResponseEntity getRequiredSubjects(@PathVariable int facultyId) {
        List<SubjectNameDto> subjectNames = facultyService.getRequiredSubjects(facultyId);
        return new ResponseEntity<>(subjectNames, HttpStatus.OK);
    }

    @RequestMapping(value = "/{facultyId}/subjects", method = RequestMethod.POST)
    public ResponseEntity addSubjectName(@PathVariable int facultyId, @RequestBody SubjectNameDto subjectNameDto) {
        facultyService.addSubjectName(facultyId, subjectNameDto.getId());
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{facultyId}/subjects/{subjectId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteSubjectName(@PathVariable int facultyId, @PathVariable int subjectId) {
        facultyService.deleteSubjectName(facultyId, subjectId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{facultyId}/users", method = RequestMethod.GET)
    public ResponseEntity getRegisteredUsers(@PathVariable int facultyId) {
        List<UserDto> users = facultyService.getRegisteredUsers(facultyId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/{facultyId}/users", method = RequestMethod.POST)
    public ResponseEntity registerToFaculty(@PathVariable int facultyId) {
        UserDto userDto = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        enrolleeService.registerToFaculty(userDto, facultyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    protected IBaseCrudService<FacultyDto> getService() {
        return facultyService;
    }
}
