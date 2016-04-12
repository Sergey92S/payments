/**
 * 
 */
package by.pvt.shmouradko.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.pvt.shmouradko.command.ActionCommand;
import by.pvt.shmouradko.command.factory.ActionFactory;
import by.pvt.shmouradko.resource.ConfigurationManager;
import by.pvt.shmouradko.resource.MessageManager;

/**
 * @author Shmouradko Sergey
 *
 */
@SuppressWarnings("serial")
@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private String pathPageIndex = "/index.jsp";
	private String messageNullpage = "Page not found. Business logic error.";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = null;
		// определение команды, пришедшей из JSP
		ActionFactory client = new ActionFactory();
		ActionCommand command = client.defineCommand(request);
		/*
		 * вызов реализованного метода execute() и передача параметров
		 * классу-обработчику конкретной команды
		 */
		page = command.execute(request);
		// метод возвращает страницу ответа
		// page = null; // поэксперементировать!
		if (page != null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
			// вызов страницы ответа на запрос
			dispatcher.forward(request, response);
		} else {
			// установка страницы c cообщением об ошибке
			page = pathPageIndex;
			request.getSession().setAttribute("nullPage", messageNullpage);
			response.sendRedirect(request.getContextPath() + page);
		}
	}
}
