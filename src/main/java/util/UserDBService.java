package util;

import dao.UserDAO;
import dao.UserDaoFactory;
import dao.UserHibernateDAO;
import dao.UserJdbcDAO;
import model.User;
import java.util.List;

public class UserDBService {

    private static UserDAO dao = null;
    private static UserDBService userDBService = null;

    private UserDBService() {
    }

    public static UserDBService getUserDBService() {
        if(userDBService == null) {
            userDBService = new UserDBService();
        }
        return userDBService;
    }

    public List<User> getAllUser() {
        UserDAO dao = UserDaoFactory.getUserDAO();
        return dao.getAllUser();
    }

    public boolean addUser(String name, int age) {
        return UserDaoFactory.getUserDAO().addUser(name, age);
    }

    public boolean deleteUser(long id) {
        return UserDaoFactory.getUserDAO().deleteUser(id);
    }

    public User getUserById(long id) {
        UserDAO dao = UserDaoFactory.getUserDAO();
        return dao.getUserById(id);
    }

    public void updateUser(User user) {
        UserDaoFactory.getUserDAO().updateUser(user);
    }


}
