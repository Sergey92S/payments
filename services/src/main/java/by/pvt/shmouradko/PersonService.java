package by.pvt.shmouradko;

import by.pvt.shmouradko.dao.PersonDAO;
import by.pvt.shmouradko.entities.Person;

import java.sql.SQLException;

/**
 * Created by Сергей on 12.04.2016.
 */
public class PersonService {
    private PersonDAO dao;
    private static PersonService instance;

    public PersonService(){
        dao = PersonDAO.getInstance();
    }

    public static PersonService getInstance() {
        if (instance == null)
            instance = new PersonService();
        return instance;
    }

    public void register(Person person) throws SQLException{
        dao.register(person);
    }

    public boolean checkLogin(String login) throws SQLException{
        return dao.checkLogin(login);
    }
}
