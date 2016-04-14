package by.pvt.shmouradko;

import by.pvt.shmouradko.dao.PersonDAO;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by Сергей on 14.04.2016.
 */
public class PersonDAOTest extends TestCase {

    @Test
    public void test() throws SQLException {
        PersonDAO personDAO = PersonDAO.getInstance();
        assertEquals(false, personDAO.checkLogin("ivan.ivanov@gmail.com"));
    }

}
