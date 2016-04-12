package by.pvt.shmouradko;

import by.pvt.shmouradko.dao.TransactionDAO;
import by.pvt.shmouradko.entities.Transaction;

import java.sql.SQLException;

/**
 * Created by Сергей on 12.04.2016.
 */
public class TransactionService {
    private TransactionDAO dao;
    private static TransactionService instance;

    public TransactionService(){
        dao = TransactionDAO.getInstance();
    }

    public static TransactionService getInstance() {
        if (instance == null)
            instance = new TransactionService();
        return instance;
    }

    public void adding(Transaction transaction) throws SQLException{
        dao.adding(transaction);
    }
}
