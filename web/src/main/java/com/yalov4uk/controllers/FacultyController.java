package com.yalov4uk.controllers;

import com.yalov4uk.abstracts.BaseCrudController;
import com.yalov4uk.dto.FacultyDto;
import com.yalov4uk.dto.StatementDto;
import com.yalov4uk.dto.SubjectNameDto;
import com.yalov4uk.dto.UserDto;
import com.yalov4uk.dto.input.SubjectNameInputDto;
import com.yalov4uk.interfaces.IAdminService;
import com.yalov4uk.interfaces.IEnrolleeService;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import com.yalov4uk.interfaces.beans.IFacultyService;
import com.yalov4uk.services.security.CustomUserDetails;
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
    private final IAdminService adminService;

    @Autowired
    public FacultyController(IFacultyService facultyService, IEnrolleeService enrolleeService,
                             IAdminService adminService) {
        this.facultyService = facultyService;
        this.enrolleeService = enrolleeService;
        this.adminService = adminService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<FacultyDto> create(@RequestBody FacultyDto faculty) {
        return super.create(faculty);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable Integer id, @RequestBody FacultyDto faculty) {
        faculty.setId(id);
        return super.update(faculty);
    }

    @RequestMapping(value = "/{facultyId}/subjectNames", method = RequestMethod.GET)
    public ResponseEntity getRequiredSubjects(@PathVariable Integer facultyId) {
        List<SubjectNameDto> subjectNames = facultyService.getRequiredSubjects(facultyId);
        return new ResponseEntity<>(subjectNames, HttpStatus.OK);
    }

    @RequestMapping(value = "/{facultyId}/subjectNames", method = RequestMethod.POST)
    public ResponseEntity addSubjectName(@PathVariable Integer facultyId,
                                         @RequestBody SubjectNameInputDto subjectNameInputDto) {
        facultyService.addSubjectName(facultyId, subjectNameInputDto.getSubjectNameId());
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{facultyId}/subjectNames/{subjectNameId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteSubjectName(@PathVariable Integer facultyId,
                                            @PathVariable Integer subjectNameId) {
        facultyService.deleteSubjectName(facultyId, subjectNameId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{facultyId}/users", method = RequestMethod.GET)
    public ResponseEntity getRegisteredUsers(@PathVariable Integer facultyId) {
        List<UserDto> users = facultyService.getRegisteredUsers(facultyId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/{facultyId}/users", method = RequestMethod.POST)
    public ResponseEntity registerToFaculty(@PathVariable Integer facultyId) {
        UserDto userDto = ((CustomUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getUserDto();
        enrolleeService.registerToFaculty(userDto.getId(), facultyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{facultyId}/pastStatements", method = RequestMethod.GET)
    public ResponseEntity calculateEntrants(@PathVariable Integer facultyId) {
        List<StatementDto> statements = adminService.calculateEntrants(facultyId);
        return new ResponseEntity<>(statements, HttpStatus.OK);
    }

    @RequestMapping(value = "/{facultyId}/pastEntrants", method = RequestMethod.GET)
    public ResponseEntity calculateEntrantsBeautifulOutput(@PathVariable Integer facultyId) {
        List<String> strings = adminService.calculateEntrantsBeautifulOutput(facultyId);
        return new ResponseEntity<>(strings, HttpStatus.OK);
    }

    protected IBaseCrudService<FacultyDto> getService() {
        return facultyService;
    }
}
