package controller.commands.authorizationCommands;

import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;
import controller.resourceManager.MessageManager;
import exceptions.InvalidPasswordException;
import exceptions.UserNotFoundException;
import model.entity.User;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Optional;

public class LoginCommand implements ActionCommand {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static UserService userService = UserService.getInstance();

    public LoginCommand() {
        userService = UserService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        Optional<String> page = Optional.empty();
        try {
            String login = request.getParameter(PARAM_NAME_LOGIN);
            String password = request.getParameter(PARAM_NAME_PASSWORD);
            saveValuesInFields(request, login, password);
            Optional<User> user = userService.login(login, password);

            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(30 * 60);
            session.setAttribute("userId", user.get().getId());
            session.setAttribute("userLogin", login);
            session.setAttribute("role", user.get().getRole());
            page = Optional.of(ConfigurationManager.getProperty("path.servlet.main"));
        } catch (UserNotFoundException e) {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginError"));
            page = Optional.of(ConfigurationManager.getProperty("path.servlet.login_page"));
        } catch (InvalidPasswordException e) {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.passwordError"));
            page = Optional.of(ConfigurationManager.getProperty("path.servlet.login_page"));
        } catch (SQLException e) {
            e.printStackTrace();// TODO: log

        }
        return page.get();
    }

    private void saveValuesInFields(HttpServletRequest request, String login, String password) {
        request.setAttribute("login_value", login);
        request.setAttribute("password_value", password);
    }

}
