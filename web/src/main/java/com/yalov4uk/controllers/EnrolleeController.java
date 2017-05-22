package com.yalov4uk.controllers;

import com.yalov4uk.abstracts.BaseController;
import com.yalov4uk.beans.Faculty;
import com.yalov4uk.beans.Statement;
import com.yalov4uk.beans.SubjectName;
import com.yalov4uk.beans.User;
import com.yalov4uk.dto.services.UserAndFaculty;
import com.yalov4uk.exceptions.NotFoundException;
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

import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity getRequiredSubjectNames(@RequestBody UserAndFaculty userAndFaculty) {
        User user = userService.read(userAndFaculty.getUser().getId());
        Faculty faculty = facultyService.read(userAndFaculty.getFaculty().getId());
        if (user == null || faculty == null || faculty.getRegisteredUsers().contains(user)) {
            throw new NotFoundException();
        }
        List<SubjectName> subjectNames = enrolleeService.getRequiredSubjectNames(user, faculty);
        logger.info("required subject names got");
        return new ResponseEntity<>(subjectNames, HttpStatus.OK);
    }

    @RequestMapping(value = "/registerToFaculty", method = RequestMethod.POST)
    public ResponseEntity registerToFaculty(@RequestBody UserAndFaculty userAndFaculty) {
        User user = userService.read(userAndFaculty.getUser().getId());
        Faculty faculty = facultyService.read(userAndFaculty.getFaculty().getId());
        if (user == null || faculty == null) {
            throw new NotFoundException();
        }
        List<Statement> statements = new ArrayList<>(faculty.getStatements());
        if (faculty.getRegisteredUsers().contains(user) || !statements.retainAll(user.getStatements())) {
            throw new RuntimeException();
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
