package com.yalov4uk.interfaces;

import com.yalov4uk.beans.Faculty;
import com.yalov4uk.beans.SubjectName;
import com.yalov4uk.beans.User;
import com.yalov4uk.interfaces.abstracts.IBaseService;

import java.util.List;

/**
 * Created by valera on 5/6/17.
 */
public interface IEnrolleeService extends IBaseService {

    List<SubjectName> getRequiredSubjectNames(User user, Faculty faculty);

    boolean registerToFaculty(User user, Faculty faculty);
}
