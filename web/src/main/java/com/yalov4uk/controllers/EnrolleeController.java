package com.yalov4uk.controllers;

import com.yalov4uk.interfaces.IEnrolleeService;
import dto.SubjectNameDto;
import dto.services.UserAndFacultyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by valera on 5/21/17.
 */
@RestController
@RequestMapping(value = "/enrollee")
public class EnrolleeController {

    @Autowired
    private IEnrolleeService enrolleeService;

    @RequestMapping(value = "/getRequiredSubjectNames", method = RequestMethod.POST)
    public ResponseEntity getRequiredSubjectNames(@RequestBody UserAndFacultyDto userAndFaculty) {
        List<SubjectNameDto> subjectNames = enrolleeService.getRequiredSubjectNames(userAndFaculty.getUser(), userAndFaculty.getFaculty());
        return new ResponseEntity<>(subjectNames, HttpStatus.OK);
    }

    @RequestMapping(value = "/registerToFaculty", method = RequestMethod.POST)
    public ResponseEntity registerToFaculty(@RequestBody UserAndFacultyDto userAndFaculty) {
        if (enrolleeService.registerToFaculty(userAndFaculty.getUser(), userAndFaculty.getFaculty())) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
