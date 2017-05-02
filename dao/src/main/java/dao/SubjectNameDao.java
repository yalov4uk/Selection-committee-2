package dao;

import abstracts.BaseDao;
import beans.SubjectName;
import interfaces.ISubjectNameDao;

/**
 * Created by valera on 5/1/17.
 */
public class SubjectNameDao extends BaseDao<SubjectName> implements ISubjectNameDao {

    public SubjectNameDao(Class<SubjectName> persistentClass) {
        super(persistentClass);
    }
}
