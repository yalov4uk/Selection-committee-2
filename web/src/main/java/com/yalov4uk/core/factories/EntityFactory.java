package com.yalov4uk.core.factories;

import com.yalov4uk.abstracts.Bean;
import com.yalov4uk.beans.*;
import com.yalov4uk.core.interfaces.IEntityFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by valera on 5/16/17.
 */
@Component
public class EntityFactory implements IEntityFactory{

    private Map<String, Class> map;

    public EntityFactory() {
        map = new HashMap<>();
        map.put("faculties", Faculty.class);
        map.put("roles", Role.class);
        map.put("statements", Statement.class);
        map.put("subjects", Subject.class);
        map.put("subjectNames", SubjectName.class);
        map.put("users", User.class);
    }

    public Class getEntity(String entityName){
        return map.get(entityName);
    }
}
