package com.yalov4uk.controllers;

import com.yalov4uk.interfaces.IAdminService;
import dto.StatementDto;
import dto.services.UserAndFacultyDto;
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
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @RequestMapping(value = "/registerStatement", method = RequestMethod.POST)
    public ResponseEntity createStatement(@RequestBody UserAndFacultyDto userAndFaculty) {
        StatementDto statement = adminService.registerStatement(userAndFaculty.getUser(), userAndFaculty.getFaculty());
        return new ResponseEntity<>(statement, HttpStatus.OK);
    }

    @RequestMapping(value = "/calculateEntrants/{facultyId}", method = RequestMethod.GET)
    public ResponseEntity calculateEntrants(@PathVariable int facultyId) {
        List<StatementDto> statements = adminService.calculateEntrants(facultyId);
        return new ResponseEntity<>(statements, HttpStatus.OK);
    }
}
