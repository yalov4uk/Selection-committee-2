package com.yalov4uk.controllers;

import com.yalov4uk.interfaces.IEnrolleeService;
import com.yalov4uk.dto.SubjectNameDto;
import com.yalov4uk.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Deprecated
@RestController
@RequestMapping(value = "/enrollee")
public class EnrolleeController {

    private final IEnrolleeService enrolleeService;

    @Autowired
    public EnrolleeController(IEnrolleeService enrolleeService) {
        this.enrolleeService = enrolleeService;
    }

    @RequestMapping(value = "/getRequiredSubjectNames/{facultyId}", method = RequestMethod.GET)
    public ResponseEntity getRequiredSubjectNames(@PathVariable Integer facultyId) {
        UserDto userDto = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<SubjectNameDto> subjectNames = enrolleeService.getRequiredSubjectNames(userDto, facultyId);
        return new ResponseEntity<>(subjectNames, HttpStatus.OK);
    }

    @RequestMapping(value = "/registerToFaculty/{facultyId}", method = RequestMethod.GET)
    public ResponseEntity registerToFaculty(@PathVariable Integer facultyId) {
        UserDto userDto = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        enrolleeService.registerToFaculty(userDto, facultyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
