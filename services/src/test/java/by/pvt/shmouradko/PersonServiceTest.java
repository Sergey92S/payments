package by.pvt.shmouradko;

import junit.framework.TestCase;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by Сергей on 14.04.2016.
 */
public class PersonServiceTest extends TestCase {

    @Test
    public void test() throws SQLException {
        PersonService personService = PersonService.getInstance();
        assertEquals(false, personService.checkLogin("ivan.ivanov@gmail.com"));
    }

}
