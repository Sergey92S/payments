/**
 * 
 */
package by.pvt.shmouradko.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.pvt.shmouradko.entities.Account;
import by.pvt.shmouradko.entities.AdminInfo;
import by.pvt.shmouradko.entities.UserInfo;
import by.pvt.shmouradko.resource.SqlManager;

/**
 * @author Shmouradko Sergey
 *
 */
public class AccountDAO extends DAO {
	private static AccountDAO instance;
	private String SQL_SELECT_ADMIN_ACCOUNT = "SELECT person.login, creditcard.name, account.count, account.status, creditcard.securitycode FROM person, creditcard, account WHERE (person.id = creditcard.Person_id) AND (creditcard.id = account.CreditCard_id) AND (account.status = 1)";
	private String SQL_ADD_ACCOUNT = "INSERT INTO account (count, CreditCard_id, status) VALUES (?, ?, ?)";
	private String SQL_SELECT_USER_ACCOUNT = "SELECT creditcard.name, creditcard.securitycode, account.count, account.status FROM creditcard, account, person WHERE (person.id = creditcard.Person_id) AND (creditcard.id = account.CreditCard_id) AND (person.login = ?)";
	private String SQL_CHECK_ACCOUNT = "SELECT creditcard.id FROM creditcard WHERE creditcard.securitycode = ?";
	private String SQL_UPDATE_STATUS = "UPDATE account INNER JOIN creditcard ON creditcard.id = account.CreditCard_id SET account.status = 2 WHERE creditcard.securitycode = ?";
	private String SQL_SELECT_ACCOUNT = "SELECT account.id FROM account, creditcard WHERE (creditcard.id = account.CreditCard_id) AND (creditcard.securitycode = ?)";

	private AccountDAO() {
		super();
	}

	public static AccountDAO getInstance() {
		if (instance == null)
			instance = new AccountDAO();
		return instance;
	}

	public void register(Account account) throws SQLException {
		Connection connection = connectionInstance.getConnection();
		//String query = sqlManager.getProperty(SqlManager.SQL_ADD_ACCOUNT);
		String query = SQL_ADD_ACCOUNT;
		PreparedStatement ps = connection.prepareStatement(query);

		ps.setInt(1, account.getCount());
		ps.setInt(2, account.getCreditCardId());
		ps.setInt(3, account.getStatus());
		ps.executeUpdate();

	}

	public List<AdminInfo> getValuesForAdmin() throws SQLException {
		Connection connection = connectionInstance.getConnection();
		String query = SQL_SELECT_ADMIN_ACCOUNT;
		Statement s = connection.createStatement();
		ResultSet result = s.executeQuery(query);
		List<AdminInfo> ai = new ArrayList<AdminInfo>();
		while (result.next()) {
			ai.add((new AdminInfo()).setCount(result.getInt("count")).setCreditcard(result.getString("name"))
					.setLogin(result.getString("login")).setStatus(result.getInt("status"))
					.setSecuritycode(result.getInt("securitycode")));
		}
		return ai;

	}

	public List<UserInfo> getValuesForUser(String login) throws SQLException {
		Connection connection = connectionInstance.getConnection();
		//String query = sqlManager.getProperty(SqlManager.SQL_SELECT_USER_ACCOUNT);
		String query = SQL_SELECT_USER_ACCOUNT;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, login);
		ResultSet result = ps.executeQuery();
		List<UserInfo> ai = new ArrayList<UserInfo>();
		while (result.next()) {
			ai.add((new UserInfo()).setName(result.getString("name")).setSecuritycode(result.getInt("securitycode"))
					.setCount(result.getInt("count")).setStatus(result.getInt("status")));
		}
		return ai;

	}

	public boolean isAccount(int securitycode) throws SQLException {
		Connection connection = connectionInstance.getConnection();
		//String query = sqlManager.getProperty(SqlManager.SQL_CHECK_ACCOUNT);
		String query = SQL_CHECK_ACCOUNT;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, securitycode);
		ResultSet result = ps.executeQuery();
		while (result.next()) {
			return true;
		}
		return false;
	}

	public void changeStatus(int securitycode) throws SQLException {
		Connection connection = connectionInstance.getConnection();
		//String query = sqlManager.getProperty(SqlManager.SQL_UPDATE_STATUS);
		String query = SQL_UPDATE_STATUS;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, securitycode);
		ps.executeUpdate();
	}

	public int getAccountId(int securitycode) throws SQLException {
		Connection connection = connectionInstance.getConnection();
		//String query = sqlManager.getProperty(SqlManager.SQL_SELECT_ACCOUNT);
		String query = SQL_SELECT_ACCOUNT;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, securitycode);
		ResultSet result = ps.executeQuery();
		while (result.next()) {
			return result.getInt("id");
		}
		return -1;
	}
}
