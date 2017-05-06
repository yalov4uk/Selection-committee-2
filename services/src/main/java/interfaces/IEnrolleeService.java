package interfaces;

import beans.Faculty;
import beans.SubjectName;
import beans.User;

import java.util.List;

/**
 * Created by valera on 5/6/17.
 */
public interface IEnrolleeService extends IBaseService {

    List<SubjectName> getRequiredSubjectNames(User user, Faculty faculty);

    Boolean registerToFaculty(User user, Faculty faculty, List<SubjectName> subjectNames, List<Integer> values);
}
