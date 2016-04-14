package by.pvt.shmouradko;

import junit.framework.TestCase;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by Сергей on 14.04.2016.
 */
public class CreditCardServiceTest extends TestCase {

    @Test
    public void test() throws SQLException {
        CreditCardService creditcardService = CreditCardService.getInstance();
        assertEquals(1, creditcardService.checkPersonId("ivan.ivanov@gmail.com"));
        assertEquals(false, creditcardService.checkSecuritycode(1111));
    }

}
