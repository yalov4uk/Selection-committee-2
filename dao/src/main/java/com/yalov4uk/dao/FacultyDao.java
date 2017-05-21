package com.yalov4uk.dao;

import com.yalov4uk.abstracts.BaseDao;
import com.yalov4uk.beans.Faculty;
import com.yalov4uk.exceptions.DaoUncheckedException;
import com.yalov4uk.interfaces.IFacultyDao;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

/**
 * Created by valera on 5/1/17.
 */
@Repository
public class FacultyDao extends BaseDao<Faculty> implements IFacultyDao {

    public Faculty findByName(String name) {
        try {
            Faculty faculty = (Faculty) entityManager.createQuery("from Faculty faculty where faculty.name = :name")
                    .setParameter("name", name)
                    .getSingleResult();
            logger.info("found by name");
            logger.debug(faculty);
            return faculty;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new DaoUncheckedException(e);
        }
    }

    protected Class<Faculty> getBeanClass(){
        return Faculty.class;
    }
}
