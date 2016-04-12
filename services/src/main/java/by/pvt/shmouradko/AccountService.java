package by.pvt.shmouradko;

import by.pvt.shmouradko.dao.AccountDAO;
import by.pvt.shmouradko.entities.Account;
import by.pvt.shmouradko.entities.AdminInfo;
import by.pvt.shmouradko.entities.UserInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Сергей on 12.04.2016.
 */
public class AccountService {
    private AccountDAO dao;
    private static AccountService instance;

    public AccountService(){
        dao = AccountDAO.getInstance();
    }

    public static AccountService getInstance() {
        if (instance == null)
            instance = new AccountService();
        return instance;
    }

    public void register(Account account) throws SQLException{
        dao.register(account);
    }

    public List<AdminInfo> getValuesForAdmin() throws SQLException{
        return dao.getValuesForAdmin();
    }

    public List<UserInfo> getValuesForUser(String login) throws SQLException{
        return dao.getValuesForUser(login);
    }

    public boolean isAccount(int securitycode) throws SQLException{
        return dao.isAccount(securitycode);
    }

    public void changeStatus(int securitycode) throws SQLException{
        dao.changeStatus(securitycode);
    }

    public int getAccountId(int securitycode) throws SQLException{
        return dao.getAccountId(securitycode);
    }
}
