package interfaces;

import beans.Faculty;

/**
 * Created by valera on 5/1/17.
 */
public interface IFacultyDao extends IBaseDao<Faculty> {

    Faculty findByName(String name);
}
