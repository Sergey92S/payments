/**
 * 
 */
package by.pvt.shmouradko.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.pvt.shmouradko.AccountService;
import by.pvt.shmouradko.LoginService;
import by.pvt.shmouradko.command.enums.ClientType;
import by.pvt.shmouradko.entities.Person;
import by.pvt.shmouradko.resource.ConfigurationManager;
import by.pvt.shmouradko.resource.MessageManager;
import org.apache.log4j.Logger;

/**
 * @author Shmouradko Sergey
 *
 */
public class LoginCommand implements ActionCommand {
	static Logger logger = Logger.getLogger(LoginCommand.class.getName());
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";
	private String pathPageUser = "/jsp/user.jsp";
	private String pathPageMain = "/jsp/main.jsp";
	private String pathPageLogin = "/jsp/login.jsp";
	private String messageLoginerror = "Incorrect login or password.";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		// извлечение из запроса логина и пароля
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String pass = request.getParameter(PARAM_NAME_PASSWORD);
		// проверка логина и пароля
		Person person = null;
		LoginService loginService = LoginService.getInstance();
		try {
			if ((person = loginService.checkPerson(login, pass, person)) != null) {
				HttpSession session = request.getSession(true);
				session.setAttribute("user", login);
				AccountService accountService = AccountService.getInstance();
				switch (person.getRole()) {
				case 1:
					session.setAttribute("userType", ClientType.USER);
					accountService = AccountService.getInstance();
					request.getSession().setAttribute("result",
							accountService.getValuesForUser(session.getAttribute("user").toString()));
					page = pathPageUser;
					break;
				case 2:
					session.setAttribute("userType", ClientType.ADMINISTRATOR);
					accountService = AccountService.getInstance();
					request.getSession().setAttribute("result", accountService.getValuesForAdmin());
					page = pathPageMain;
					break;
				default:
					session.setAttribute("userType", ClientType.GUEST);
					page = pathPageLogin;
				}
				accountService = null;
			} else {
				request.setAttribute("errorLoginPassMessage", messageLoginerror);
				request.getSession().setAttribute("userType", ClientType.GUEST);
				page = pathPageLogin;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.debug("LoginCommand returned: " + page);
		return page;
	}

}
