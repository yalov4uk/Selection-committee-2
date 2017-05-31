package com.yalov4uk.controllers.beans;

import com.yalov4uk.abstracts.BaseCrudController;
import com.yalov4uk.dto.SubjectDto;
import com.yalov4uk.dto.UserDto;
import com.yalov4uk.interfaces.IClientService;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import com.yalov4uk.interfaces.beans.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController extends BaseCrudController<UserDto> {

    private final IUserService userService;
    private final IClientService clientService;

    @Autowired
    public UserController(IUserService userService, IClientService clientService) {
        this.userService = userService;
        this.clientService = clientService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody UserDto user) {
        user = clientService.register(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<UserDto> update(@RequestBody UserDto user) {
        return updateCrud(user);
    }

    @RequestMapping(value = "/{userId}/subjects", method = RequestMethod.GET)
    public ResponseEntity getSubjects(@PathVariable int userId) {
        List<SubjectDto> subjects = userService.getSubjects(userId);
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @RequestMapping(value = "/myAccount", method = RequestMethod.GET)
    public ResponseEntity getMyAccount() {
        UserDto userDto = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(userService.read(userDto.getId()), HttpStatus.OK);
    }

    @RequestMapping(value = "/myAccount/subjects", method = RequestMethod.GET)
    public ResponseEntity getMySubjects() {
        UserDto userDto = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<SubjectDto> subjects = userService.getSubjects(userDto.getId());
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    protected IBaseCrudService<UserDto> getService() {
        return userService;
    }
}
