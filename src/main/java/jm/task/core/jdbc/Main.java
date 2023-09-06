package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
public static void main(String[] args) {
//    Util.getConn();
//    UserDao userDao = new UserDaoJDBCImpl();
    UserDao userDao = new UserDaoHibernateImpl();
    userDao.createUsersTable();
    userDao.saveUser("Name11", "LastName11", (byte) 21);
    userDao.saveUser("Name1", "LastName1", (byte) 20);
    userDao.saveUser("Name2", "LastName2", (byte) 25);
    userDao.saveUser("Name3", "LastName3", (byte) 31);
    userDao.saveUser("Name4", "LastName4", (byte) 38);

    userDao.removeUserById(1);
    System.out.println(userDao.getAllUsers());
    userDao.cleanUsersTable();
    System.out.println(userDao.getAllUsers());
    userDao.dropUsersTable();

}
}
