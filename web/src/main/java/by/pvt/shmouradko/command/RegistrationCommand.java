/**
 * 
 */
package by.pvt.shmouradko.command;

import static by.pvt.shmouradko.utils.FormDataValidator.namePattern;
import static by.pvt.shmouradko.utils.FormDataValidator.surnamePattern;
import static by.pvt.shmouradko.utils.FormDataValidator.loginPattern;
import static by.pvt.shmouradko.utils.FormDataValidator.passwordPattern;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.pvt.shmouradko.PersonService;
import by.pvt.shmouradko.command.enums.ClientType;
import by.pvt.shmouradko.entities.Person;
import by.pvt.shmouradko.resource.ConfigurationManager;
import by.pvt.shmouradko.resource.MessageManager;
import org.apache.log4j.Logger;

/**
 * @author Shmouradko Sergey
 *
 */
public class RegistrationCommand implements ActionCommand {
	static Logger logger = Logger.getLogger(LoginCommand.class.getName());
	private final String PARAM_NAME_PERSON = "name";
	private final String PARAM_SURNAME_PERSON = "surname";
	private final String PARAM_LOGIN_PERSON = "loginPerson";
	private final String PARAM_PASSWORD_PERSON = "passwordPerson";
	private final int PARAM_ROLE_PERSON = 1;
	private String pathPageLogin = "/jsp/login.jsp";
	private String pathPageRegistration = "/jsp/registration.jsp";
	private String messageLoginregistrationerror = "This login is already exist.";
	private String messageRegistrationerror = "Not all fields are filled.";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		// извлечение из запроса полей регистрации
		String name = request.getParameter(PARAM_NAME_PERSON);
		String surname = request.getParameter(PARAM_SURNAME_PERSON);
		String login = request.getParameter(PARAM_LOGIN_PERSON);
		String password = request.getParameter(PARAM_PASSWORD_PERSON);
		int role = PARAM_ROLE_PERSON;
		Person person = new Person();
		person.setName(name.trim());
		person.setSurname(surname.trim());
		person.setLogin(login.trim());
		person.setPassword(password.trim());
		person.setRole(role);
		PersonService personService = PersonService.getInstance();
		try {
			// проверка повторяющегося логина
			if (!personService.checkLogin(person.getLogin())) {
				request.setAttribute("errorLoginRegistrationPassMessage",
						messageLoginregistrationerror);
				request.getSession().setAttribute("userType", ClientType.GUEST);
				page = pathPageLogin;
				return page;
			}
			// проверка полей и определение пути к registration.jsp
			if (validation(person)) {
				personService.register(person);
				request.setAttribute("user", login);
				HttpSession session = request.getSession(true);
				session.setAttribute("userType", ClientType.USER);
				page = pathPageRegistration;
			} else {
				request.setAttribute("errorRegistrationPassMessage", messageRegistrationerror);
				request.getSession().setAttribute("userType", ClientType.GUEST);
				page = pathPageLogin;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.debug("RegistrationCommand returned: " + page);
		return page;
	}

	public boolean validation(Person person) {
		// проверка имени
		if (!namePattern.matcher(person.getName()).matches()) {
			// если совпадение не найдено
			return false;
		}

		// проверка фамилии
		if (!surnamePattern.matcher(person.getSurname()).matches()) {
			// если совпадение не найдено
			return false;
		}

		// проверка логина
		if (!loginPattern.matcher(person.getLogin()).matches()) {
			// если совпадение не найдено
			return false;
		}

		// проверка пароля
		if (!passwordPattern.matcher(person.getPassword()).matches()) {
			// если совпадение не найдено
			return false;
		}

		return true;
	}

}
