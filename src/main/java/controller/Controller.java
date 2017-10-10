package controller;

import controller.commands.ActionCommand;
import controller.commands.ActionFactory;
import controller.resourceManager.ConfigurationManager;
import controller.resourceManager.MessageManager;
import org.apache.log4j.Logger;

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
    private static final Logger log = Logger.getLogger(Controller.class);

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
            log.warn("attempt of access to nonexistent page: "+ page);
            page = Optional.of(ConfigurationManager.getProperty("path.page.index"));
            request.getSession().setAttribute("nullPage", ((MessageManager) request.getSession()
                    .getAttribute("messageManager")).getProperty("message.nullPage"));
            response.sendRedirect(request.getContextPath() + page.get());
        }
    }
}
