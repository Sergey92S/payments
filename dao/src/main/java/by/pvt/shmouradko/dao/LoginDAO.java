/**
 * 
 */
package by.pvt.shmouradko.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.pvt.shmouradko.entities.Person;
import by.pvt.shmouradko.resource.SqlManager;
import by.pvt.shmouradko.utils.Coder;

/**
 * @author Shmouradko Sergey
 *
 */
public class LoginDAO extends DAO {
	private static final String PARAM_NAME_PERSON = "name";
	private static final String PARAM_SURNAME_PERSON = "surname";
	private static final String PARAM_LOGIN_PERSON = "login";
	private static final String PARAM_PASSWORD_PERSON = "password";
	private static final String PARAM_ROLE_PERSON = "role";
	private static LoginDAO instance;
	private String SQL_CHECK_PERSON = "SELECT * FROM person WHERE (login = ?) AND (password = ?)";

	private LoginDAO() {
		super();
	}

	public static LoginDAO getInstance() {
		if (instance == null)
			instance = new LoginDAO();
		return instance;
	}

	/*
	*This method checks person
	* @param enterLogin - person's login, enterPass - person's password, person - entity of person
	* @return person with filled fields
	 */
	public Person checkPerson(String enterLogin, String enterPass, Person person) throws SQLException {
		Connection connection = connectionInstance.getConnection();
		//String query = sqlManager.getProperty(SqlManager.SQL_CHECK_PERSON);
		String query = SQL_CHECK_PERSON;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, enterLogin);
		ps.setString(2, Coder.getHashCode(enterPass));
		ResultSet result = ps.executeQuery();
		if (result.next()) {
			person = new Person();
			person.setName(result.getString(PARAM_NAME_PERSON));
			person.setSurname(result.getString(PARAM_SURNAME_PERSON));
			person.setLogin(result.getString(PARAM_LOGIN_PERSON));
			person.setPassword(result.getString(PARAM_PASSWORD_PERSON));
			person.setRole(result.getInt(PARAM_ROLE_PERSON));
		}
		return person;
	}

}
