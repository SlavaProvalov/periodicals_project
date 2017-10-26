package controller.commands.authorizationCommands;

import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;
import controller.resourceManager.MessageManager;
import controller.utils.ErrorConstructor;
import exceptions.EmailAlreadyExistException;
import exceptions.LoginAlreadyExistException;
import model.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Optional;

public class SignUpCommand implements ActionCommand {
    private static SignUpCommand instance;
    private static Logger log;
    private static UserService service;

    private SignUpCommand() {
        service = UserService.getInstance();
        log = Logger.getLogger(SignUpCommand.class);
    }

    public static SignUpCommand getInstance() {
        if (instance == null) {
            instance = new SignUpCommand();
        }
        return instance;
    }

    @Override
    public Optional<String> execute(HttpServletRequest request) {
        Optional<String> page;
        HttpSession session = request.getSession();
        MessageManager messageManager = (MessageManager) session.getAttribute("messageManager");
        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String phone = request.getParameter("phone");
            saveValuesInFields(request, login, password, email, firstName, lastName, phone);
            service.createNewUser(login, password, firstName, lastName, email, phone);
            request.setAttribute("success", messageManager.getProperty("message.success.signUp"));
            request.setAttribute("redirect", messageManager.getProperty("message.success.redirect"));
            request.setAttribute("redirectServlet", ConfigurationManager.getProperty("path.servlet.welcome"));
            page = Optional.of(ConfigurationManager.getProperty("path.page.success"));
        } catch (LoginAlreadyExistException e) {
            request.setAttribute("errorLogin", 1);
            page = Optional.of(ConfigurationManager.getProperty("path.servlet.sign_up_page"));
        } catch (EmailAlreadyExistException e) {
            request.setAttribute("errorEmail", 1);
            page = Optional.of(ConfigurationManager.getProperty("path.servlet.sign_up_page"));
        } catch (SQLException e) {
            log.error(e);
            page = Optional.of(ConfigurationManager.getProperty("path.page.error"));
            ErrorConstructor.fillErrorPage(request, e, "path.servlet.sign_up_page");
        }
        return page;
    }

    private void saveValuesInFields(HttpServletRequest request, String login, String password, String email, String firstName, String lastName, String phone) {
        request.setAttribute("login_value", login);
        request.setAttribute("password_value", password);
        request.setAttribute("email_value", email);
        request.setAttribute("firstName_value", firstName);
        request.setAttribute("lastName_value", lastName);
        request.setAttribute("phone_value", phone);
        request.setAttribute("errorLogin", 0);
        request.setAttribute("errorEmail", 0);
    }


}
