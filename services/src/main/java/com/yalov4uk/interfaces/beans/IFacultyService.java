package com.yalov4uk.interfaces.beans;

import com.yalov4uk.beans.Faculty;
import com.yalov4uk.beans.SubjectName;
import com.yalov4uk.interfaces.abstracts.IBaseCrudService;

/**
 * Created by valera on 5/17/17.
 */
public interface IFacultyService extends IBaseCrudService<Faculty> {

    void addSubjectName(Faculty faculty, SubjectName subjectName);

    void deleteSubjectName(Faculty faculty, SubjectName subjectName);
}
