package com.yalov4uk.core.interfaces;

import com.yalov4uk.abstracts.Bean;

/**
 * Created by valera on 5/16/17.
 */
public interface IEntityFactory {

    Class getEntity(String entityName);
}
