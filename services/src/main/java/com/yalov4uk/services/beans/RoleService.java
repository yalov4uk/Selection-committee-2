package com.yalov4uk.services.beans;

import com.yalov4uk.abstracts.BaseCrudService;
import com.yalov4uk.abstracts.BaseDtoValidator;
import com.yalov4uk.beans.Role;
import com.yalov4uk.dto.RoleDto;
import com.yalov4uk.interfaces.IBaseDao;
import com.yalov4uk.interfaces.IRoleDao;
import com.yalov4uk.interfaces.beans.IRoleService;
import com.yalov4uk.validators.RoleValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BaseCrudService<Role, RoleDto> implements IRoleService {

    private final IRoleDao roleDao;
    private final RoleValidator roleValidator;

    @Autowired
    public RoleService(ModelMapper modelMapper, IRoleDao roleDao, RoleValidator roleValidator) {
        super(modelMapper);
        this.roleDao = roleDao;
        this.roleValidator = roleValidator;
    }

    protected IBaseDao<Role> getDao() {
        return roleDao;
    }

    protected Class<Role> getBeanClass() {
        return Role.class;
    }

    protected Class<RoleDto> getDtoClass() {
        return RoleDto.class;
    }

    protected BaseDtoValidator<RoleDto> getValidator(){
        return roleValidator;
    }
}
