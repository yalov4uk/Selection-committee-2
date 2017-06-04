package com.yalov4uk.controllers;

import com.yalov4uk.dto.SubjectDto;
import com.yalov4uk.dto.UserDto;
import com.yalov4uk.dto.input.SubjectInputDto;
import com.yalov4uk.interfaces.beans.ISubjectService;
import com.yalov4uk.interfaces.beans.IUserService;
import com.yalov4uk.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/self")
public class SelfController {

    private final IUserService userService;
    private final ISubjectService subjectService;

    @Autowired
    public SelfController(IUserService userService, ISubjectService subjectService) {
        this.userService = userService;
        this.subjectService = subjectService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getMyAccount() {
        UserDto curUser = getSelf();
        return new ResponseEntity<>(userService.read(curUser.getId()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody UserDto user) {
        UserDto curUser = getSelf();
        user.setId(curUser.getId());
        user.setRole(curUser.getRole());
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/subjects", method = RequestMethod.GET)
    public ResponseEntity getMySubjects() {
        UserDto curUser = getSelf();
        List<SubjectDto> subjects = userService.getSubjects(curUser.getId());
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @RequestMapping(value = "/subjects", method = RequestMethod.POST)
    public ResponseEntity addMySubject(@RequestBody SubjectInputDto subject) {
        UserDto curUser = getSelf();
        subject.setUserId(curUser.getId());
        return new ResponseEntity<>(subjectService.create(subject), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/subjects/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteMySubject(@PathVariable Integer id) {
        UserDto curUser = getSelf();
        userService.deleteSubject(curUser.getId(), id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private UserDto getSelf(){
        return ((CustomUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getUserDto();
    }
}
