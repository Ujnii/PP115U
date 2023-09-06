package jm.task.core.jdbc.dao;

import com.sun.xml.fastinfoset.util.StringArray;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;



public class UserDaoHibernateImpl implements UserDao {

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS mydbtest.users (id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT, name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, age TINYINT)";
    private static final String DROP_TABLE = "DROP TABLE mydbtest.users";
    private static final String CLEAN_TABLE = "DELETE FROM mydbtest.users";
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getFactory().openSession()) {
            session.getTransaction().begin();
            session.createNativeQuery(CREATE_TABLE).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getFactory().openSession()) {
            session.getTransaction().begin();
            session.createNativeQuery(DROP_TABLE).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getFactory().openSession()) {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        User user = null;
        try (Session session = Util.getFactory().openSession()){
            transaction = session.beginTransaction();

            user = session.get(User.class, id);
            session.delete(user);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> list = null;
        try (Session session = Util.getFactory().openSession()) {
        transaction = session.beginTransaction();

        list = session.createQuery("from User").list();

        transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getFactory().openSession()) {
            session.getTransaction().begin();
            session.createNativeQuery(CLEAN_TABLE).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
