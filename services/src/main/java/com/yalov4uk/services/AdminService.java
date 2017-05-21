package com.yalov4uk.services;

import com.yalov4uk.abstracts.BaseService;
import com.yalov4uk.beans.Faculty;
import com.yalov4uk.beans.Statement;
import com.yalov4uk.beans.User;
import com.yalov4uk.exceptions.ServiceUncheckedException;
import com.yalov4uk.interfaces.IAdminService;
import com.yalov4uk.interfaces.IFacultyDao;
import com.yalov4uk.interfaces.IStatementDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by valera on 5/3/17.
 */
@Service
public class AdminService extends BaseService implements IAdminService {

    private final IStatementDao statementDao;
    private final IFacultyDao facultyDao;

    @Autowired
    public AdminService(IStatementDao statementDao, IFacultyDao facultyDao) {
        this.statementDao = statementDao;
        this.facultyDao = facultyDao;
    }

    public Statement registerStatement(User user, Faculty faculty) {
        try {
            Statement statement = new Statement();
            statement.setFaculty(faculty);
            statement.setUser(user);
            statementDao.persist(statement);

            faculty.getStatements().add(statement);
            facultyDao.update(faculty);
            logger.info("register statement");
            logger.debug(statement);
            return statement;
        } catch (Exception e) {
            logger.error("not register statement");
            throw new ServiceUncheckedException(e);
        }
    }

    public List<Statement> calculateEntrants(Faculty faculty) {
        try {
            List<Statement> objects =  statementDao.getAll()
                    .stream()
                    .filter(statement -> statement.getFaculty() == faculty)
                    .sorted((a, b) -> {
                        int x = a.getUser().getAverageScore(a.getFaculty());
                        int y = b.getUser().getAverageScore(b.getFaculty());
                        return y - x;
                    })
                    .limit(faculty.getMaxSize())
                    .collect(Collectors.toList());
            logger.info("calculated entrants");
            return objects;
        } catch (Exception e) {
            logger.error("not calculated entrants");
            throw new ServiceUncheckedException(e);
        }
    }
}
