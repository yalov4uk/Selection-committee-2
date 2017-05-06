package interfaces;

import beans.Faculty;
import beans.Statement;
import beans.User;

import java.util.List;

/**
 * Created by valera on 5/6/17.
 */
public interface IAdminService extends IBaseService {

    Statement registerStatement(User user, Faculty faculty);

    List<Statement> calculateEntrants(List<Statement> statements);
}
