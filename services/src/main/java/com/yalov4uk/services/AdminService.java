package com.yalov4uk.services;

import com.yalov4uk.abstracts.BaseService;
import com.yalov4uk.beans.Faculty;
import com.yalov4uk.beans.Statement;
import com.yalov4uk.beans.User;
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
        Statement statement = new Statement();
        statement.setFaculty(faculty);
        statement.setUser(user);
        statementDao.persist(statement);

        faculty.getStatements().add(statement);
        facultyDao.update(faculty);
        return statement;
    }

    public List<Statement> calculateEntrants(List<Statement> statements) {
        return statements
                .stream()
                .sorted((a, b) -> {
                    int x = a.getUser().getAverageScore(a.getFaculty());
                    int y = b.getUser().getAverageScore(b.getFaculty());
                    return y - x;
                })
                .limit(statements.size() > 0 ? statements.get(0).getFaculty().getMaxSize() : 0)
                .collect(Collectors.toList());
    }
}
