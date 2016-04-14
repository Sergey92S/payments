/**
 * 
 */
package by.pvt.shmouradko.command;

import static by.pvt.shmouradko.utils.FormDataValidator.creditcardPattern;
import static by.pvt.shmouradko.utils.FormDataValidator.securitycodePattern;
import static by.pvt.shmouradko.utils.FormDataValidator.countPattern;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import by.pvt.shmouradko.AccountService;
import by.pvt.shmouradko.CreditCardService;
import by.pvt.shmouradko.entities.Account;
import by.pvt.shmouradko.entities.CreditCard;
import by.pvt.shmouradko.resource.ConfigurationManager;
import by.pvt.shmouradko.resource.MessageManager;
import org.apache.log4j.Logger;

/**
 * @author Shmouradko Sergey
 *
 */
public class CreditCardCommand implements ActionCommand {
	static Logger logger = Logger.getLogger(LoginCommand.class.getName());
	private static final String PARAM_NAME_CREDITCARD = "name";
	private static final String PARAM_SECURITYCODE_CREDITCARD = "securitycode";
	private static final String PARAM_COUNT_ACCOUNT = "count";
	private static int PARAM_PERSONID_CREDITCARD;
	private String pathPageUser = "/jsp/user.jsp";
	private String pathPageCreditcard = "/jsp/creditcard.jsp";
	private String messageSecuritycodeerror = "This Security Code is already exist.";
	private String messageCreditcarderror = "Not all fields are filled.";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		String creditcardPerson = request.getParameter(PARAM_NAME_CREDITCARD);
		CreditCardService creditcardService = CreditCardService.getInstance();
		AccountService accountService = AccountService.getInstance();
		CreditCard creditcard = new CreditCard();
		Account account = new Account();
		try {
			if (validation(creditcardPerson.trim(), request.getParameter(PARAM_SECURITYCODE_CREDITCARD).trim(),
					request.getParameter(PARAM_COUNT_ACCOUNT).trim())) {
				int securitycodePerson = Integer.parseInt(request.getParameter(PARAM_SECURITYCODE_CREDITCARD));
				int countPerson = Integer.parseInt(request.getParameter(PARAM_COUNT_ACCOUNT));
				if ((PARAM_PERSONID_CREDITCARD = creditcardService
						.checkPersonId(request.getSession(true).getAttribute("user").toString())) != -1) {
					creditcard.setName(creditcardPerson.trim());
					creditcard.setSecuritycode(securitycodePerson);
					creditcard.setPersonId(PARAM_PERSONID_CREDITCARD);
					account.setCount(countPerson);
					account.setStatus(1);
					if (!creditcardService.checkSecuritycode(creditcard.getSecuritycode())) {
						request.setAttribute("errorSecurityCodePassMessage",
								messageSecuritycodeerror);
						page = pathPageUser;
						return page;
					}
					if (validation(creditcard, account)) {
						account.setCreditCardId(creditcardService.register(creditcard));
						accountService.register(account);
						page = pathPageCreditcard;
					} else {
						request.setAttribute("errorCreditCardPassMessage", messageCreditcarderror);
						page = pathPageUser;
					}
				}
			} else {
				request.setAttribute("errorCreditCardPassMessage", messageCreditcarderror);
				page = pathPageUser;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.debug("CreditCardCommand returned: " + page);
		return page;
	}

	public boolean validation(CreditCard creditcard, Account account) {
		if ((creditcard.getName() == null) || (creditcard.getName().equals(""))) {
			return false;
		}
		if ((creditcard.getSecuritycode() == 0) || (creditcard.getSecuritycode() == -1)) {
			return false;
		}
		if ((account.getCount() == 0) || (account.getCount() == -1)) {
			return false;
		}
		return true;
	}

	public boolean validation(String creditcardPerson, String securitycodePerson, String countPerson) {

		// проверка названия кредитной карты
		if (!creditcardPattern.matcher(creditcardPerson).matches()) {
			// если совпадение не найдено
			return false;
		}

		// проверка кода безопасности
		if (!securitycodePattern.matcher(securitycodePerson).matches()) {
			// если совпадение не найдено
			return false;
		}

		// проверка баланса
		if (!countPattern.matcher(countPerson).matches()) {
			// если совпадение не найдено
			return false;
		}

		return true;

	}

}
