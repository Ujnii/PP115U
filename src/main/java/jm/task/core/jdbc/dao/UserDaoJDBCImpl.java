package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS mydbtest.users (id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT, name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, age TINYINT)";
    private static final String DROP_TABLE = "DROP TABLE mydbtest.users";
    private static final String SAVE_USER = "INSERT INTO mydbtest.users (name, lastName, age) VALUES (?,?,?)";
    private static final String REMOVE_USER = "DELETE FROM mydbtest.users WHERE id = ?";
    private static final String GET_USERS = "SELECT * FROM mydbtest.users";
    private static final String CLEAN_TABLE = "DELETE FROM mydbtest.users";
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConn()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_TABLE);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConn()) {
            PreparedStatement statement = connection.prepareStatement(DROP_TABLE);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConn()) {
            PreparedStatement statement = connection.prepareStatement(SAVE_USER);
            statement.setString(1,name);
            statement.setString(2,lastName);
            statement.setByte(3,age);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConn()) {
            PreparedStatement statement = connection.prepareStatement(REMOVE_USER);
            statement.setLong(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection = Util.getConn()) {
            PreparedStatement statement = connection.prepareStatement(GET_USERS);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConn()) {
            PreparedStatement statement = connection.prepareStatement(CLEAN_TABLE);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
