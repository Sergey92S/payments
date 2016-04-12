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
public class PersonDAO extends DAO {
	private static PersonDAO instance;
	private String SQL_ADD_PERSON = "INSERT INTO person(name, surname, login, password, role) VALUES (?, ?, ?, ?, ?)";
	private String SQL_CHECK_LOGIN = "SELECT id FROM person WHERE login = ?";

	private PersonDAO() {
		super();
	}

	public static PersonDAO getInstance() {
		if (instance == null)
			instance = new PersonDAO();
		return instance;
	}

	public void register(Person person) throws SQLException {
		Connection connection = connectionInstance.getConnection();
		//String query = sqlManager.getProperty(SqlManager.SQL_ADD_PERSON);
		String query = SQL_ADD_PERSON;
		PreparedStatement ps = connection.prepareStatement(query);

		ps.setString(1, person.getName());
		ps.setString(2, person.getSurname());
		ps.setString(3, person.getLogin());
		ps.setString(4, Coder.getHashCode(person.getPassword()));
		ps.setLong(5, person.getRole());
		ps.executeUpdate();

	}

	public boolean checkLogin(String login) throws SQLException {
		Connection connection = connectionInstance.getConnection();
		//String query = sqlManager.getProperty(SqlManager.SQL_CHECK_LOGIN);
		String query = SQL_CHECK_LOGIN;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, login);
		ResultSet result = ps.executeQuery();
		if (result.next()) {
			return false;
		}
		return true;
	}

}
