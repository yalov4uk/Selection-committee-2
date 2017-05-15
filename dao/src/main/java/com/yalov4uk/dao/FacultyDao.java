package com.yalov4uk.dao;

import com.yalov4uk.abstracts.BaseDao;
import com.yalov4uk.beans.Faculty;
import com.yalov4uk.interfaces.IFacultyDao;
import org.springframework.stereotype.Repository;

/**
 * Created by valera on 5/1/17.
 */
@Repository
public class FacultyDao extends BaseDao<Faculty> implements IFacultyDao {

    public FacultyDao() {
        super(Faculty.class);
    }

    public Faculty findByName(String name) {
        return (Faculty) entityManager.createQuery("from Faculty faculty where faculty.name = :name")
                .setParameter("name", name)
                .getSingleResult();
    }
}
