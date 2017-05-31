package com.yalov4uk.controllers;

import com.yalov4uk.interfaces.IAdminService;
import com.yalov4uk.dto.StatementDto;
import com.yalov4uk.dto.UserAndFacultyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Deprecated
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private final IAdminService adminService;

    @Autowired
    public AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(value = "/registerStatement", method = RequestMethod.POST)
    public ResponseEntity createStatement(@RequestBody UserAndFacultyDto userAndFaculty) {
        StatementDto statement = adminService.registerStatement(userAndFaculty.getUserId(), userAndFaculty.getFacultyId());
        return new ResponseEntity<>(statement, HttpStatus.OK);
    }

    @RequestMapping(value = "/calculateEntrants/{facultyId}", method = RequestMethod.GET)
    public ResponseEntity calculateEntrants(@PathVariable int facultyId) {
        List<StatementDto> statements = adminService.calculateEntrants(facultyId);
        return new ResponseEntity<>(statements, HttpStatus.OK);
    }
}
