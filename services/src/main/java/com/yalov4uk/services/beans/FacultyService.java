package com.yalov4uk.services.beans;

import com.yalov4uk.abstracts.BaseCrudService;
import com.yalov4uk.beans.Faculty;
import com.yalov4uk.beans.SubjectName;
import com.yalov4uk.exceptions.ServiceUncheckedException;
import com.yalov4uk.interfaces.IBaseDao;
import com.yalov4uk.interfaces.IFacultyDao;
import com.yalov4uk.interfaces.ISubjectNameDao;
import com.yalov4uk.interfaces.beans.IFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by valera on 5/17/17.
 */
@Service
public class FacultyService extends BaseCrudService<Faculty> implements IFacultyService {

    private final IFacultyDao facultyDao;
    private final ISubjectNameDao subjectNameDao;

    @Autowired
    public FacultyService(IFacultyDao facultyDao, ISubjectNameDao subjectNameDao) {
        this.facultyDao = facultyDao;
        this.subjectNameDao = subjectNameDao;
    }

    public void addSubjectName(Faculty faculty, SubjectName subjectName) {
        try {
            faculty.getRequiredSubjects().add(subjectName);
            logger.info("added subject name");
            logger.debug(subjectName);
        } catch (Exception e) {
            logger.error("not added subject name");
            throw new ServiceUncheckedException(e);
        }
    }

    public void deleteSubjectName(Faculty faculty, SubjectName subjectName) {
        try {
            faculty.getRequiredSubjects().remove(subjectName);
            logger.info("deleted subject name");
            logger.debug(subjectName);
        } catch (Exception e) {
            logger.error("not deleted subject name");
            throw new ServiceUncheckedException(e);
        }
    }

    protected IBaseDao<Faculty> getDao() {
        return facultyDao;
    }
}
