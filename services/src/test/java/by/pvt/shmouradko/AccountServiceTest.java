package by.pvt.shmouradko;

import junit.framework.TestCase;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by Сергей on 14.04.2016.
 */
public class AccountServiceTest extends TestCase {

    @Test
    public void test() throws SQLException {
        AccountService accountService = AccountService.getInstance();
        //assertTrue(accountService.getValuesForAdmin().size() == 0);
        assertTrue(accountService.getValuesForUser("ivan.ivanov@gmail.com").size() == 1);
        assertEquals(true, accountService.isAccount(1111));
        assertEquals(1, accountService.getAccountId(1111));
    }

}
