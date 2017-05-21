package com.yalov4uk.controllers;

import com.yalov4uk.beans.Faculty;
import com.yalov4uk.beans.User;
import com.yalov4uk.controllers.abstracts.BaseController;
import com.yalov4uk.dto.SubjectNameDto;
import com.yalov4uk.dto.services.UserAndFacultyDto;
import com.yalov4uk.interfaces.IEnrolleeService;
import com.yalov4uk.interfaces.beans.IFacultyService;
import com.yalov4uk.interfaces.beans.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by valera on 5/21/17.
 */
@RestController
@RequestMapping(value = "/enrollee")
public class EnrolleeController extends BaseController {

    @Autowired
    private IEnrolleeService enrolleeService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IFacultyService facultyService;

    @RequestMapping(value = "/getRequiredSubjectNames", method = RequestMethod.POST)
    public ResponseEntity getRequiredSubjectNames(@RequestBody UserAndFacultyDto userAndFacultyDto) {
        User user = userService.read(userAndFacultyDto.getUser().getId());
        Faculty faculty = facultyService.read(userAndFacultyDto.getFaculty().getId());
        if (user == null || faculty == null || faculty.getRegisteredUsers().contains(user)) {
            throw new NullPointerException();
        }
        List<SubjectNameDto> subjectNames = enrolleeService.getRequiredSubjectNames(user, faculty)
                .stream()
                .map(subjectName -> modelMapper.map(subjectName, SubjectNameDto.class))
                .collect(Collectors.toList());
        logger.info("required subject names got");
        return new ResponseEntity<>(subjectNames, HttpStatus.OK);
    }

    @RequestMapping(value = "/registerToFaculty", method = RequestMethod.POST)
    public ResponseEntity registerToFaculty(@RequestBody UserAndFacultyDto userAndFacultyDto) {
        User user = userService.read(userAndFacultyDto.getUser().getId());
        Faculty faculty = facultyService.read(userAndFacultyDto.getFaculty().getId());
        if (user == null || faculty == null || faculty.getRegisteredUsers().contains(user)) {
            throw new NullPointerException();
        }
        if (enrolleeService.registerToFaculty(user, faculty)) {
            logger.info("registered to faculty");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            logger.error("not enough subjects");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
