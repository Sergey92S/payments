/**
 * 
 */
package by.pvt.shmouradko.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.pvt.shmouradko.entities.CreditCard;
import by.pvt.shmouradko.resource.SqlManager;

/**
 * @author Shmouradko Sergey
 *
 */
public class CreditCardDAO extends DAO {
	private static CreditCardDAO instance;
	private String SQL_CHECK_LOGIN = "SELECT id FROM person WHERE login = ?";
	private String SQL_CHECK_SECURITYCODE = "SELECT id FROM creditcard WHERE securitycode = ?";
	private String SQL_ADD_CREDITCARD = "INSERT INTO creditcard (name, securitycode, Person_id) VALUES (?, ?, ?)";

	private CreditCardDAO() {
		super();
	}

	public static CreditCardDAO getInstance() {
		if (instance == null)
			instance = new CreditCardDAO();
		return instance;
	}

	/*
	*This method checks person's id
	* @param login - person's login
	* @return id - person's id
	 */
	public int checkPersonId(String login) throws SQLException {
		Connection connection = connectionInstance.getConnection();
		//String query = sqlManager.getProperty(SqlManager.SQL_CHECK_LOGIN);
		String query = SQL_CHECK_LOGIN;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, login);
		ResultSet result = ps.executeQuery();
		if (result.next()) {
			return result.getInt("id");
		}
		return -1;
	}

	/*
	* This method checks security code
	* @param securitycode - account's security code
	* @return false if such security code has already exsist
	 */
	public boolean checkSecuritycode(int securitycode) throws SQLException {
		Connection connection = connectionInstance.getConnection();
		//String query = sqlManager.getProperty(SqlManager.SQL_CHECK_SECURITYCODE);
		String query = SQL_CHECK_SECURITYCODE;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, securitycode);
		ResultSet result = ps.executeQuery();
		if (result.next()) {
			return false;
		}
		return true;
	}

	/*
	*This method registers a new credit card
	* @param creditcard - person's credit card
	* @return id of such credit card
	 */
	public int register(CreditCard creditcard) throws SQLException {
		Connection connection = connectionInstance.getConnection();
		//String query = sqlManager.getProperty(SqlManager.SQL_ADD_CREDITCARD);
		String query = SQL_ADD_CREDITCARD;
		PreparedStatement ps = connection.prepareStatement(query);

		ps.setString(1, creditcard.getName());
		ps.setInt(2, creditcard.getSecuritycode());
		ps.setInt(3, creditcard.getPersonId());
		ps.executeUpdate();

		ps = null;
		query = null;
		//query = sqlManager.getProperty(SqlManager.SQL_CHECK_SECURITYCODE);
		query = SQL_CHECK_SECURITYCODE;
		ps = connection.prepareStatement(query);
		ps.setInt(1, creditcard.getSecuritycode());
		ResultSet result = ps.executeQuery();
		if (result.next()) {
			return result.getInt("id");
		}
		return -1;
	}
}
