package controller;

import controller.commands.ActionCommand;
import controller.commands.ActionFactory;
import controller.resourceManager.ConfigurationManager;
import controller.resourceManager.MessageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/")
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        Optional<String> page = Optional.ofNullable(command.execute(request));

        if (page.isPresent()) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page.get());
            dispatcher.forward(request, response);
        } else {
            page = Optional.of(ConfigurationManager.getProperty("path.page.index"));
            request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullPage"));
            response.sendRedirect(request.getContextPath() + page.get());
        }
    }
}
