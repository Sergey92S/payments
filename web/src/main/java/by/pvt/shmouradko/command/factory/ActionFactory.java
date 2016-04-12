/**
 * 
 */
package by.pvt.shmouradko.command.factory;

import javax.servlet.http.HttpServletRequest;

import by.pvt.shmouradko.command.ActionCommand;
import by.pvt.shmouradko.command.EmptyCommand;
import by.pvt.shmouradko.command.enums.CommandEnum;

/**
 * @author Shmouradko Sergey
 *
 */
public class ActionFactory {
	private String messageWrongaction = ": command not found or wrong!";

	public ActionCommand defineCommand(HttpServletRequest request) {
		ActionCommand current = new EmptyCommand();
		// извлечение имени команды из запроса
		String action = request.getParameter("command");
		if (action == null || action.isEmpty()) {
			// если команда не задана в текущем запросе
			return current;
		}
		// получение объекта, соответствующего команде
		try {
			CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());

			current = currentEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			request.setAttribute("wrongAction", action + messageWrongaction);
		}
		return current;
	}
}
