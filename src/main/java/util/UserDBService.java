package util;

import dao.UserDAO;
import dao.UserJdbcDAO;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserDBService {

    private static UserDAO dao = null;

    public UserDBService() {
    }

    public List<User> getAllUser() {
        UserDAO dao = getUserDAO();
        return dao.getAllUser();
    }

    public boolean addUser(String name, int age) {
        return getUserDAO().addUser(name, age);
    }

    public boolean deleteUser(long id) {
        UserDAO dao = getUserDAO();
        return dao.deleteUser(id);
    }

    public User getUserById(long id) {
        UserDAO dao = getUserDAO();
        return dao.getUserById(id);
    }

    private static UserDAO getUserDAO() {
        if(dao == null) {
            dao = new UserJdbcDAO(getMysqlConnection());
        }
        return dao;
    }

    public void updateUser(User user) {
        getUserDAO().updateUser(user);
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("pre1?").          //db name
                    append("user=zaa&").          //login
                    append("password=Control1");       //password
            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}
