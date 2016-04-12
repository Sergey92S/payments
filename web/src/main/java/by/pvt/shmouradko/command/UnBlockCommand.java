/**
 * 
 */
package by.pvt.shmouradko.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import by.pvt.shmouradko.AccountService;
import by.pvt.shmouradko.resource.ConfigurationManager;
import by.pvt.shmouradko.resource.MessageManager;

/**
 * @author Shmouradko Sergey
 *
 */
public class UnBlockCommand implements ActionCommand {

	private static final String PARAM_SECURITYCODE_ADMIN = "securitycode";
	private String pathPageMain = "/jsp/main.jsp";
	private String pathPageUnblock = "/jsp/unblock.jsp";
	private String messageAccounterror = "There is no such account.";
	private String messageValidationerror = "Not all fields are filled.";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		AccountService accountService = AccountService.getInstance();
		try {
			if (validation(request.getParameter(PARAM_SECURITYCODE_ADMIN).trim())) {
				// извлечение из запроса поля кода безопасности
				int securitycode = Integer.parseInt(request.getParameter(PARAM_SECURITYCODE_ADMIN));
				if (!accountService.isAccount(securitycode)) {
					request.setAttribute("errorAccountPassMessage", messageAccounterror);
					page = pathPageMain;
					return page;
				}
				accountService.changeStatus(securitycode);
				page = pathPageUnblock;
			} else {
				request.setAttribute("errorValidationPassMessage", messageValidationerror);
				page = pathPageMain;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return page;
	}

	public boolean validation(String securitycode) {
		if ((securitycode == null) || (securitycode.equals(""))) {
			return false;
		}
		return true;
	}

}
