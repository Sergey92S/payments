package by.pvt.shmouradko;

import by.pvt.shmouradko.dao.AccountDAO;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by Сергей on 13.04.2016.
 */
public class AccountDAOTest extends TestCase{

    @Test
    public void test() throws SQLException {
        AccountDAO accountDAO = AccountDAO.getInstance();
        //assertTrue(accountDAO.getValuesForAdmin().size() == 0);
        assertTrue(accountDAO.getValuesForUser("ivan.ivanov@gmail.com").size() == 1);
        assertEquals(true, accountDAO.isAccount(1111));
        assertEquals(1, accountDAO.getAccountId(1111));
    }

}
