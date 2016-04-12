/**
 * 
 */
package by.pvt.shmouradko.dao;

import by.pvt.shmouradko.dataSources.DBConnection;
import by.pvt.shmouradko.dataSources.DataSource;
import by.pvt.shmouradko.resource.SqlManager;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Shmouradko Sergey
 *
 */
public abstract class DAO {
	protected static DataSource connectionInstance;
	protected static SqlManager sqlManager;

	protected DAO() {
		try {
			connectionInstance = DataSource.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		//sqlManager = SqlManager.getInstance();
	}
}
