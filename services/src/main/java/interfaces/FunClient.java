package interfaces;

/**
 * Created by valera on 5/3/17.
 */
public interface FunClient<T> {

    T execute(String... args);
}
