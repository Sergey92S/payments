/**
 * 
 */
package by.pvt.shmouradko.resource;

import java.util.ResourceBundle;

/**
 * @author Shmouradko Sergey
 *
 */
public class ConfigurationManager {
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

	// класс извлекает информацию из файла config. properties
	private ConfigurationManager() {
	}

	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
