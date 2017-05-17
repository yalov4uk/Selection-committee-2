package com.yalov4uk.core.controllers.beans;

import com.yalov4uk.beans.Role;
import com.yalov4uk.core.controllers.abstracts.CrudController;
import com.yalov4uk.dto.RoleDto;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;
import com.yalov4uk.interfaces.beans.IRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by valera on 5/17/17.
 */
@RestController
@RequestMapping(value = "/roles")
public class RoleController extends CrudController<Role> {

    private final IRoleService roleService;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleController(IRoleService roleService, ModelMapper modelMapper) {
        super(Role.class);
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody RoleDto roleDto) {
        return createCrud(roleDto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody RoleDto roleDto) {
        return updateCrud(roleDto);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestBody RoleDto roleDto) {
        return deleteCrud(roleDto);
    }

    protected IBaseCrudService<Role> getService() {
        return roleService;
    }

    protected ModelMapper getMapper() {
        return modelMapper;
    }
}
