package com.yalov4uk.controllers;

import com.yalov4uk.beans.Faculty;
import com.yalov4uk.beans.Statement;
import com.yalov4uk.beans.User;
import com.yalov4uk.controllers.abstracts.BaseController;
import com.yalov4uk.dto.StatementDto;
import com.yalov4uk.dto.services.UserAndFacultyDto;
import com.yalov4uk.interfaces.IAdminService;
import com.yalov4uk.interfaces.beans.IFacultyService;
import com.yalov4uk.interfaces.beans.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by valera on 5/17/17.
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {

    private final IAdminService adminService;
    private final IUserService userService;
    private final IFacultyService facultyService;

    @Autowired
    public AdminController(IAdminService adminService, IUserService userService, IFacultyService facultyService) {
        this.adminService = adminService;
        this.userService = userService;
        this.facultyService = facultyService;
    }

    @RequestMapping(value = "/registerStatement", method = RequestMethod.POST)
    public ResponseEntity createStatement(@RequestBody UserAndFacultyDto userAndFacultyDto) {
        User user = userService.read(userAndFacultyDto.getUser().getId());
        Faculty faculty = facultyService.read(userAndFacultyDto.getFaculty().getId());
        if (user == null || faculty == null || !faculty.getRegisteredUsers().contains(user)) {
            throw new NullPointerException();
        }
        Statement statement = adminService.registerStatement(user, faculty);
        StatementDto statementDto = modelMapper.map(statement, StatementDto.class);
        logger.info("statement registered");
        logger.debug(statement);
        return new ResponseEntity<>(statementDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/calculateEntrants/{facultyId}", method = RequestMethod.GET)
    public ResponseEntity calculateEntrants(@PathVariable String facultyId) {
        Faculty faculty = facultyService.read(Integer.parseInt(facultyId));
        if (faculty == null) {
            throw new NullPointerException();
        }
        List<StatementDto> statements = adminService.calculateEntrants(faculty)
                .stream()
                .map(statement -> modelMapper.map(statement, StatementDto.class))
                .collect(Collectors.toList());
        logger.info("entrants calculated");
        return new ResponseEntity<>(statements, HttpStatus.OK);
    }
}
