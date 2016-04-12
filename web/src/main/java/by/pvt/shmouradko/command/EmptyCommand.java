/**
 * 
 */
package by.pvt.shmouradko.command;

import javax.servlet.http.HttpServletRequest;

import by.pvt.shmouradko.resource.ConfigurationManager;

/**
 * @author Shmouradko Sergey
 *
 */
public class EmptyCommand implements ActionCommand {
	private String pathPageLogin = "/jsp/login.jsp";

	@Override
	public String execute(HttpServletRequest request) {
		/*
		 * в случае ошибки или прямого обращения к контроллеру переадресация на
		 * страницу ввода логина
		 */
		String page = pathPageLogin;
		return page;
	}

}
