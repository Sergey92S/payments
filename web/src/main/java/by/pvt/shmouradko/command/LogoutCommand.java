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
public class LogoutCommand implements ActionCommand {
	static Logger logger = Logger.getLogger(LoginCommand.class.getName());
	private String pathPageUser = "/jsp/user.jsp";
	private String pathPageMain = "/jsp/main.jsp";
	private String pathPageLogin = "/jsp/login.jsp";

	@Override
	public String execute(HttpServletRequest request) {
		String action = request.getParameter("option");
		String page = null;
		if (action.equals("userReturn")) {
			page = pathPageUser;
		} else if (action.equals("adminReturn")) {
			page = pathPageMain;
		} else {
			page = pathPageLogin;
			// уничтожение сессии
			request.getSession().invalidate();
		}
		logger.debug("LogoutCommand returned: " + page);
		return page;
	}

}
