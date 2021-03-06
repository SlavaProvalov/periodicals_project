package controller.commands.userCommands;

import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;
import controller.utils.ErrorConstructor;
import exceptions.EmailAlreadyExistException;
import model.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Optional;

public class UserUpdateCommand implements ActionCommand {
    private static UserUpdateCommand instance;
    private static Logger log;
    private static UserService userService;


    private UserUpdateCommand() {
        userService = UserService.getInstance();
        log = Logger.getLogger(UserUpdateCommand.class);
    }

    public static UserUpdateCommand getInstance() {
        if (instance == null) {
            instance = new UserUpdateCommand();
        }
        return instance;
    }

    @Override
    public Optional<String> execute(HttpServletRequest request) {
        Optional<String> page;
        HttpSession session = request.getSession();
        try {
            int id = (int) session.getAttribute("userId");
            String role = (String) session.getAttribute("role");
            String login = (String) session.getAttribute("userLogin");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String phone = request.getParameter("phone");
            boolean checkEmail;
            if (email.equalsIgnoreCase((String) session.getAttribute("email"))) {
                checkEmail = false;
            } else {
                checkEmail = true;
            }
            saveValuesInFields(request, email, firstName, lastName, phone);
            userService.updateUser(id, login, password, role, firstName, lastName, email, checkEmail, phone);
            page = Optional.of(ConfigurationManager.getProperty("path.servlet.user_details"));
        } catch (EmailAlreadyExistException e) {
            request.setAttribute("emailError", 1);
            page = Optional.of(ConfigurationManager.getProperty("path.servlet.user_update_page"));
        } catch (SQLException e) {
            log.error(e);
            page = Optional.of(ConfigurationManager.getProperty("path.page.error"));
            ErrorConstructor.fillErrorPage(request, e, "path.servlet.user_update_page");
        }
        return page;
    }

    private void saveValuesInFields(HttpServletRequest request, String email, String firstName, String lastName, String phone) {
        request.setAttribute("emailError", 0);
        request.setAttribute("email_prev", email);
        request.setAttribute("firstName_prev", firstName);
        request.setAttribute("lastName_prev", lastName);
        request.setAttribute("phone_prev", phone);
    }
}
