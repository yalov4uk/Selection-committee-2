package com.yalov4uk.interfaces;

import com.yalov4uk.beans.Faculty;
import com.yalov4uk.beans.Statement;
import com.yalov4uk.beans.User;
import com.yalov4uk.interfaces.abstracts.IBaseService;

import java.util.List;

/**
 * Created by valera on 5/6/17.
 */
public interface IAdminService extends IBaseService {

    Statement registerStatement(User user, Faculty faculty);

    List<Statement> calculateEntrants(Faculty faculty);
}
