package com.yalov4uk.services.beans;

import com.yalov4uk.abstracts.BaseCrudService;
import com.yalov4uk.beans.Role;
import com.yalov4uk.dto.RoleDto;
import com.yalov4uk.interfaces.IBaseDao;
import com.yalov4uk.interfaces.IRoleDao;
import com.yalov4uk.interfaces.beans.IRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BaseCrudService<Role, RoleDto> implements IRoleService {

    private final IRoleDao roleDao;

    @Autowired
    public RoleService(ModelMapper modelMapper, IRoleDao roleDao) {
        super(modelMapper);
        this.roleDao = roleDao;
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
}
