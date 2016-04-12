/**
 * 
 */
package by.pvt.shmouradko.resource;

import java.util.ResourceBundle;

/**
 * @author Shmouradko Sergey
 *
 */
public class MessageManager {
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

	// класс извлекает информацию из файла messages. properties
	private MessageManager() {
	}

	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
