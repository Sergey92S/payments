/**
 * 
 */
package by.pvt.shmouradko.command;

import static by.pvt.shmouradko.utils.FormDataValidator.sumPattern;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import by.pvt.shmouradko.AccountService;
import by.pvt.shmouradko.TransactionService;
import by.pvt.shmouradko.entities.Transaction;
import by.pvt.shmouradko.resource.ConfigurationManager;
import by.pvt.shmouradko.resource.MessageManager;

/**
 * @author Shmouradko Sergey
 *
 */
public class AddingAccountCommand implements ActionCommand {

	private static final String PARAM_NAME_USER = "name";
	private static final String PARAM_SECURITYCODE_USER = "securitycode";
	private static final String PARAM_SUM_USER = "sum";
	private String pathPageUser = "/jsp/user.jsp";
	private String pathPageAdding = "/jsp/adding.jsp";
	private String messageCheckaccounterror = "There is no such account.";
	private String messageValidationerror = "Not all fields are filled.";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		TransactionService transactionService = TransactionService.getInstance();
		Transaction transaction = new Transaction();
		AccountService accountService = AccountService.getInstance();
		try {
			if (validation(request.getParameter(PARAM_NAME_USER).trim(), request.getParameter(PARAM_SECURITYCODE_USER).trim(),
					request.getParameter(PARAM_SUM_USER).trim())) {
				int securitycode = Integer.parseInt(request.getParameter(PARAM_SECURITYCODE_USER));
				int sum = Integer.parseInt(request.getParameter(PARAM_SUM_USER));
				if (!accountService.isAccount(securitycode)) {
					request.setAttribute("errorCheckAccountPassMessage", messageCheckaccounterror);
					page = pathPageUser;
					return page;
				}
				transaction.setSum(sum);
				transaction.setAccountId(accountService.getAccountId(securitycode));
				transactionService.adding(transaction);
				page = pathPageAdding;
			} else {
				request.setAttribute("errorValidationPassMessage", messageValidationerror);
				page = pathPageUser;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return page;
	}

	public boolean validation(String name, String securitycode, String sum) {
		if ((name == null) || (name.equals(""))) {
			return false;
		}

		if ((securitycode == null) || (securitycode.equals(""))) {
			return false;
		}

		if ((sum == null) || (sum.equals(""))) {
			return false;
		}

		// проверка баланса
		if (!sumPattern.matcher(sum).matches()) {
			// если совпадение не найдено
			return false;
		}

		return true;
	}

}
