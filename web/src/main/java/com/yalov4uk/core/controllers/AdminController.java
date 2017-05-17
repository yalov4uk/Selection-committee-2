package com.yalov4uk.core.controllers;

import com.yalov4uk.beans.Faculty;
import com.yalov4uk.beans.Statement;
import com.yalov4uk.beans.User;
import com.yalov4uk.dto.StatementDto;
import com.yalov4uk.dto.services.UserAndFacultyDto;
import com.yalov4uk.interfaces.IAdminService;
import com.yalov4uk.interfaces.beans.IFacultyService;
import com.yalov4uk.interfaces.beans.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by valera on 5/17/17.
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IFacultyService facultyService;
    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/registerStatement", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody UserAndFacultyDto userAndFacultyDto) {
        User user = userService.read(userAndFacultyDto.getUser().getId());
        Faculty faculty = facultyService.read(userAndFacultyDto.getFaculty().getId());
        if (user == null || faculty == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Statement statement = adminService.registerStatement(user, faculty);
        StatementDto statementDto = modelMapper.map(statement, StatementDto.class);
        return new ResponseEntity<>(statementDto, HttpStatus.OK);
    }
}
