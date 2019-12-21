package dao;

import java.io.*;
import java.util.Properties;

public class UserDaoFactory {

    public static final String configDAO = "/DAO.property";

    public static UserDAO getUserDAO() {
        Properties prop = new Properties();
        try {
            InputStream is = UserDaoFactory.class.getResourceAsStream(configDAO);
            prop.load(is);
            return (UserDAO) Class.forName(prop.getProperty("DAO_type")).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return new UserHibernateDAO();
        }
    }
}
