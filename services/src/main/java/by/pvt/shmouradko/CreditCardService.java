package by.pvt.shmouradko;

import by.pvt.shmouradko.dao.CreditCardDAO;
import by.pvt.shmouradko.entities.CreditCard;

import java.sql.SQLException;

/**
 * Created by Сергей on 12.04.2016.
 */
public class CreditCardService {
    private CreditCardDAO dao;
    private static CreditCardService instance;

    public CreditCardService(){
        dao = CreditCardDAO.getInstance();
    }

    public static CreditCardService getInstance() {
        if (instance == null)
            instance = new CreditCardService();
        return instance;
    }

    public int checkPersonId(String login) throws SQLException{
        return dao.checkPersonId(login);
    }

    public boolean checkSecuritycode(int securitycode) throws SQLException{
        return dao.checkSecuritycode(securitycode);
    }

    public int register(CreditCard creditcard) throws SQLException{
        return dao.register(creditcard);
    }
}
