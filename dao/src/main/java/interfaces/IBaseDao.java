package interfaces;

import abstracts.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by valera on 4/30/17.
 */
public interface IBaseDao<T extends Bean> {

    Serializable save(T object);

    void persist(T object);

    T find(Integer key);

    void update(T object);

    void delete(Integer key);

    List<T> getAll();
}
