package dao;

import abstracts.BaseDao;
import beans.Subject;
import interfaces.ISubjectDao;

/**
 * Created by valera on 5/1/17.
 */
public class SubjectDao extends BaseDao<Subject> implements ISubjectDao {

    public SubjectDao(Class<Subject> persistentClass) {
        super(persistentClass);
    }
}
