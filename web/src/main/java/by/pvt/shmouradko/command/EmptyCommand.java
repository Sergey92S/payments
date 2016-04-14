/**
 * 
 */
package by.pvt.shmouradko.command;

import javax.servlet.http.HttpServletRequest;

import by.pvt.shmouradko.resource.ConfigurationManager;
import org.apache.log4j.Logger;

/**
 * @author Shmouradko Sergey
 *
 */
public class EmptyCommand implements ActionCommand {
	static Logger logger = Logger.getLogger(LoginCommand.class.getName());
	private String pathPageLogin = "/jsp/login.jsp";

	@Override
	public String execute(HttpServletRequest request) {
		/*
		 * в случае ошибки или прямого обращения к контроллеру переадресация на
		 * страницу ввода логина
		 */
		String page = pathPageLogin;
		logger.debug("EmptyCommand returned: " + page);
		return page;
	}

}
