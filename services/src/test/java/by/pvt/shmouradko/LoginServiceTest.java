package by.pvt.shmouradko;

import junit.framework.TestCase;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by Сергей on 14.04.2016.
 */
public class LoginServiceTest extends TestCase {

    @Test
    public void test() throws SQLException {
        LoginService loginService = LoginService.getInstance();
        assertTrue(loginService.checkPerson("ivan.ivanov@gmail.com", "123456", null).getName().equals("ivan"));
    }

}
