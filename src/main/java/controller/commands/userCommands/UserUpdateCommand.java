package controller.commands.userCommands;

import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;
import controller.utils.ErrorConstructor;
import exceptions.EmailAlreadyExistException;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Optional;

public class UserUpdateCommand implements ActionCommand {
    private static UserService userService;


    public UserUpdateCommand() {
        userService = UserService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        Optional<String> page = Optional.empty();
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
        } catch (SQLException e) {// // TODO: 08.10.2017 log
            page = Optional.of(ConfigurationManager.getProperty("path.page.error"));
            ErrorConstructor.fillErrorMessage(request, e, "path.servlet.user_update_page");
        }
        return page.get();
    }

    private void saveValuesInFields(HttpServletRequest request, String email, String firstName, String lastName, String phone) {
        request.setAttribute("emailError", 0);
        request.setAttribute("email_prev", email);
        request.setAttribute("firstName_prev", firstName);
        request.setAttribute("lastName_prev", lastName);
        request.setAttribute("phone_prev", phone);
    }
}
