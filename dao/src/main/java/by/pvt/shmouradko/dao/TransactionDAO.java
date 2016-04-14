/**
 * 
 */
package by.pvt.shmouradko.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.pvt.shmouradko.entities.Transaction;
import by.pvt.shmouradko.resource.SqlManager;

/**
 * @author Shmouradko Sergey
 *
 */
public class TransactionDAO extends DAO {
	private static TransactionDAO instance;
	String SQL_SELECT_ACCOUNT = "SELECT person.login, creditcard.name, account.count, account.status, creditcard.securitycode FROM person, creditcard, account WHERE (person.id = creditcard.Person_id) AND (creditcard.id = account.CreditCard_id) AND (account.status = 1)";
	private String SQL_INSERT_TRANSACTION = "INSERT INTO transactions(sum, Account_id) VALUES (?, ?)";
	private String SQL_UPDATE_COUNT = "UPDATE account SET account.count = (account.count + ?) WHERE account.id = ?";

	private TransactionDAO() {
		super();
	}

	public static TransactionDAO getInstance() {
		if (instance == null)
			instance = new TransactionDAO();
		return instance;
	}

	/*
	*This method create a new transaction and adding person's count
	* @param transaction - a new transaction
	 */
	public void adding(Transaction transaction) throws SQLException {
		Connection connection = connectionInstance.getConnection();
		//String query = sqlManager.getProperty(SqlManager.SQL_INSERT_TRANSACTION);
		String query = SQL_INSERT_TRANSACTION;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, transaction.getSum());
		ps.setInt(2, transaction.getAccountId());
		ps.executeUpdate();
		// добавить изменение баланса в таблице аккаунтов
		query = null;
		ps = null;
		//query = sqlManager.getProperty(SqlManager.SQL_UPDATE_COUNT);
		query = SQL_UPDATE_COUNT;
		ps = connection.prepareStatement(query);
		ps.setInt(1, transaction.getSum());
		ps.setInt(2, transaction.getAccountId());
		ps.executeUpdate();
	}
}
