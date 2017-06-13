package com.yalov4uk.dao;

import com.yalov4uk.abstracts.BaseDao;
import com.yalov4uk.beans.Subject;
import com.yalov4uk.exceptions.DaoUncheckedException;
import com.yalov4uk.interfaces.ISubjectDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class SubjectDao extends BaseDao<Subject> implements ISubjectDao {

    protected Class<Subject> getBeanClass() {
        return Subject.class;
    }

    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }

    @Override
    public Subject findBySubjectNameAndUserIds(Integer subjectNameId, Integer userId) {
        try {
            Subject subject = (Subject) entityManager
                    .createQuery("from Subject subject where subject.subjectName.id = :subjectNameId " +
                            "and subject.user.id = :userId")
                    .setParameter("subjectNameId", subjectNameId)
                    .setParameter("userId", userId)
                    .getSingleResult();
            logger.info("found by subjectNameId");
            logger.debug("subject " + subject.toString() + " was found");
            return subject;
        } catch (NoResultException e) {
            logger.error("subject not found");
            return null;
        } catch (Exception e) {
            logger.error("find by subjectNameId error cause: " + e.getMessage());
            throw new DaoUncheckedException("Error while find subject by subjectNameId");
        }
    }
}
