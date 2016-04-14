package by.pvt.shmouradko;

import by.pvt.shmouradko.dao.CreditCardDAO;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by Сергей on 14.04.2016.
 */
public class CreditCardDAOTest extends TestCase {

    @Test
    public void test() throws SQLException {
        CreditCardDAO creditcardDAO = CreditCardDAO.getInstance();
        assertEquals(1, creditcardDAO.checkPersonId("ivan.ivanov@gmail.com"));
        assertEquals(false, creditcardDAO.checkSecuritycode(1111));
    }

}
