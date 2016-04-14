package by.pvt.shmouradko;

import by.pvt.shmouradko.dao.LoginDAO;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by Сергей on 14.04.2016.
 */
public class LoginDAOTest extends TestCase {

    @Test
    public void test() throws SQLException {
        LoginDAO loginDAO = LoginDAO.getInstance();
        assertTrue(loginDAO.checkPerson("ivan.ivanov@gmail.com", "123456", null).getName().equals("ivan"));
    }

}
