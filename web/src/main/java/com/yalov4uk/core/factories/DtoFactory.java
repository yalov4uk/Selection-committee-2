package com.yalov4uk.core.factories;

import com.yalov4uk.abstracts.Dto;
import com.yalov4uk.beans.*;
import com.yalov4uk.core.interfaces.IDtoFactory;
import com.yalov4uk.dto.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by valera on 5/17/17.
 */
@Component
public class DtoFactory implements IDtoFactory{

    private Map<Class, Class> map;

    public DtoFactory() {
        map = new HashMap<>();
        map.put(Faculty.class, FacultyDto.class);
        map.put(Role.class, RoleDto.class);
        map.put(Statement.class, StatementDto.class);
        map.put(Subject.class, SubjectDto.class);
        map.put(SubjectName.class, SubjectNameDto.class);
        map.put(User.class, UserDto.class);
    }

    public Class getDto(Class clazz){
        return map.get(clazz);
    }
}
