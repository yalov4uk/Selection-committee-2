package com.yalov4uk.controllers;

import com.yalov4uk.abstracts.BaseController;
import com.yalov4uk.beans.Faculty;
import com.yalov4uk.beans.Statement;
import com.yalov4uk.beans.User;
import com.yalov4uk.dto.services.UserAndFaculty;
import com.yalov4uk.exceptions.NotFoundException;
import com.yalov4uk.interfaces.IAdminService;
import com.yalov4uk.interfaces.beans.IFacultyService;
import com.yalov4uk.interfaces.beans.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by valera on 5/17/17.
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IFacultyService facultyService;

    @RequestMapping(value = "/registerStatement", method = RequestMethod.POST)
    public ResponseEntity createStatement(@RequestBody UserAndFaculty userAndFaculty) {
        User user = userService.read(userAndFaculty.getUser().getId());
        Faculty faculty = facultyService.read(userAndFaculty.getFaculty().getId());
        if (user == null || faculty == null || !faculty.getRegisteredUsers().contains(user)) {
            throw new NotFoundException();
        }
        Statement statement = adminService.registerStatement(user, faculty);
        logger.info("statement registered");
        logger.debug(statement);
        return new ResponseEntity<>(statement, HttpStatus.OK);
    }

    @RequestMapping(value = "/calculateEntrants/{facultyId}", method = RequestMethod.GET)
    public ResponseEntity calculateEntrants(@PathVariable String facultyId) {
        Faculty faculty = facultyService.read(Integer.parseInt(facultyId));
        if (faculty == null) {
            throw new NotFoundException();
        }
        List<Statement> statements = adminService.calculateEntrants(faculty);
        logger.info("entrants calculated");
        return new ResponseEntity<>(statements, HttpStatus.OK);
    }
}
