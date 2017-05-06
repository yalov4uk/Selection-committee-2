package abstracts;

import exceptions.ServiceUncheckedException;
import interfaces.IBaseService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;
import java.util.function.Supplier;

/**
 * Created by valera on 5/3/17.
 */
public abstract class BaseService implements IBaseService {

    protected HibernateUtil hibernateUtil;

    public BaseService() {
        hibernateUtil = HibernateUtil.getInstance();
    }

    protected <T> T baseCommand(Supplier<T> function){
        Session session = hibernateUtil.getSession();
        Transaction transaction = null;
        T object = null;
        try {
            transaction = session.beginTransaction();

            object  = function.get();

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw new ServiceUncheckedException(e);
        } finally {

        }
        return object;
    }
}
