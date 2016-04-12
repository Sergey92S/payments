package by.pvt.shmouradko;

import by.pvt.shmouradko.dao.LoginDAO;
import by.pvt.shmouradko.entities.Person;

import java.sql.SQLException;

/**
 * Created by Сергей on 12.04.2016.
 */
public class LoginService {
    private LoginDAO dao;
    private static LoginService instance;

    public LoginService(){
        dao = LoginDAO.getInstance();
    }

    public static LoginService getInstance() {
        if (instance == null)
            instance = new LoginService();
        return instance;
    }

    public Person checkPerson(String enterLogin, String enterPass, Person person) throws SQLException{
        return dao.checkPerson(enterLogin, enterPass, person);
    }
}
