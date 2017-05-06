package tools;

import abstracts.BaseService;
import beans.Faculty;
import beans.Statement;
import beans.User;
import dao.FacultyDao;
import dao.StatementDao;
import interfaces.IAdminService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by valera on 5/3/17.
 */
public class AdminService extends BaseService implements IAdminService {

    public Statement registerStatement(User user, Faculty faculty) {
        return baseCommand(() -> {
            StatementDao statementDao = new StatementDao(Statement.class);
            Statement statement = new Statement();
            statement.setFaculty(faculty);
            statement.setUser(user);
            statementDao.persist(statement);

            FacultyDao facultyDao = new FacultyDao(Faculty.class);
            faculty.getStatements().add(statement);
            facultyDao.update(faculty);
            return statement;
        });
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
