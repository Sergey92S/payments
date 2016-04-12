/**
 * 
 */
package by.pvt.shmouradko.resource;

import java.util.ResourceBundle;

/**
 * @author Shmouradko Sergey
 *
 */
public class SqlManager {

	private static SqlManager instance;
	private static ResourceBundle bundle;

	public static final String BUNDLE_NAME = "sql";

	// //#LOGIN DAO
	// public static final String SQL_GET_USER = "SQL_GET_USER";
	// public static final String SQL_GET_USER_BY_UID = "SQL_GET_USER_BY_UID";
	//
	// //#ADMINISTRATOR DAO
	// public static final String SQL_ADD_FILM = "SQL_ADD_FILM";
	// public static final String SQL_SET_ORDER_STATUS = "SQL_SET_ORDER_STATUS";
	// public static final String SQL_DELETE_FILM = "SQL_DELETE_FILM";
	// public static final String SQL_UPDATE_ORDER_STATUS =
	// "SQL_UPDATE_ORDER_STATUS";
	// public static final String SQL_GET_ALL_USERS = "SQL_GET_ALL_USERS";
	// public static final String SQL_SET_ACCESS_LEVEL = "SQL_SET_ACCESS_LEVEL";
	//
	// //#FILMS DAO
	// public static final String SQL_GET_FILM_BY_ID = "SQL_GET_FILM_BY_ID";
	// public static final String SQL_GET_ALL_FILMS = "SQL_GET_ALL_FILMS";
	// public static final String SQL_GET_FILMS_BY_GENRE =
	// "SQL_GET_FILMS_BY_GENRE";
	// public static final String SQL_SEARCH_FILM = "SQL_SEARCH_FILM";
	// public static final String SQL_UPDATE_FILM = "SQL_UPDATE_FILM";
	// public static final String SQL_COUNT_FILMS = "SQL_COUNT_FILMS";
	//
	// //#ORDERS DAO
	// public static final String SQL_GET_ALL_ORDERS = "SQL_GET_ALL_ORDERS";
	// public static final String SQL_GET_ORDERS_BY_UID = "SQL_GET_ORDERS_BY_UID";
	// public static final String SQL_GET_ORDERS_BY_NAME =
	// "SQL_GET_ORDERS_BY_NAME";
	// public static final String SQL_GET_ORDERS_BY_STATUS =
	// "SQL_GET_ORDERS_BY_STATUS";
	// public static final String SQL_ADD_ORDER = "SQL_ADD_ORDER";
	//
	// //#USER DAO
	// public static final String SQL_ADD_USER = "SQL_ADD_USER";
	// public static final String SQL_GET_ACCESS_LEVEL = "SQL_GET_ACCESS_LEVEL";
	// public static final String SQL_CHECK_LOGIN = "SQL_CHECK_LOGIN";

	// -------------------------------------------------------------------------
	
	// #PERSON DAO
	public static final String SQL_ADD_PERSON = "SQL_ADD_PERSON";
	public static final String SQL_CHECK_LOGIN = "SQL_CHECK_LOGIN";
	//
	// #LOGIN DAO
	public static final String SQL_CHECK_PERSON = "SQL_CHECK_PERSON";
	//
	// #CREDITCARD DAO
	public static final String SQL_CHECK_SECURITYCODE = "SQL_CHECK_SECURITYCODE";
	public static final String SQL_ADD_CREDITCARD = "SQL_ADD_CREDITCARD";
	//
	// #ACCOUNT DAO
	public static final String SQL_ADD_ACCOUNT = "SQL_ADD_ACCOUNT";
	public static final String SQL_CHECK_ACCOUNT = "SQL_CHECK_ACCOUNT";
	public static final String SQL_UPDATE_STATUS = "SQL_UPDATE_STATUS";
	public static final String SQL_SELECT_ACCOUNT = "SQL_SELECT_ACCOUNT";
	public static final String SQL_SELECT_USER_ACCOUNT = "SQL_SELECT_USER_ACCOUNT";
	//
	// #TRANSACTION DAO
	public static final String SQL_INSERT_TRANSACTION = "SQL_INSERT_TRANSACTION";
	public static final String SQL_UPDATE_COUNT = "SQL_UPDATE_COUNT";

	public static SqlManager getInstance() {
		if (instance == null) {
			instance = new SqlManager();
			bundle = ResourceBundle.getBundle(BUNDLE_NAME);
		}
		return instance;
	}

	public String getProperty(String key) {
		return bundle.getString(key);
	}

}
