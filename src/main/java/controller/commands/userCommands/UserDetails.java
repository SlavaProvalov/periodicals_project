package controller.commands.userCommands;

import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;
import controller.resourceManager.PageContextManager;
import model.entity.Client;
import model.entity.Periodical;
import model.service.PeriodicalService;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDetails implements ActionCommand {
    private static UserService userService;
    private static PeriodicalService periodicalService;

    public UserDetails() {
        userService = UserService.getInstance();
        periodicalService = PeriodicalService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        Optional<String> page = Optional.empty();
        HttpSession session = request.getSession();
        try {
            int id = (int) session.getAttribute("userId");
            Optional<Client> client = userService.getClientById(id);
            List<Periodical> periodicals = periodicalService.getPeriodicalsByClientId(id);

            setAttributes(request, session, client.get(), periodicals);

            page = Optional.of(ConfigurationManager.getProperty("path.page.userDetails"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return page.get();
    }

    private void setAttributes(HttpServletRequest request, HttpSession session, Client client, List<Periodical> periodicals) {
        request.setAttribute("logout", PageContextManager.getProperty("authorization.logout"));
        request.setAttribute("main", PageContextManager.getProperty("main.menu"));
        request.setAttribute("cart", PageContextManager.getProperty("main.cart"));
        request.setAttribute("items", session.getAttribute("items"));
        request.setAttribute("hi", PageContextManager.getProperty("main.hi"));
        request.setAttribute("userLogin", session.getAttribute("userLogin"));
        request.setAttribute("user_login", PageContextManager.getProperty("signUp.login"));
        request.setAttribute("user_role", PageContextManager.getProperty("user.role"));
        request.setAttribute("user_firstName", PageContextManager.getProperty("signUp.name"));
        request.setAttribute("user_lastName", PageContextManager.getProperty("signUp.lastName"));
        request.setAttribute("user_email", PageContextManager.getProperty("signUp.email"));
        request.setAttribute("user_phoneNumber", PageContextManager.getProperty("user.phoneNumber"));
        request.setAttribute("login", session.getAttribute("userLogin"));
        request.setAttribute("role", session.getAttribute("role"));
        request.setAttribute("firstName", client.getFirstName());
        request.setAttribute("lastName", client.getLastName());
        request.setAttribute("email", client.getEmail());
        request.setAttribute("phoneNumber", client.getPhoneNumber());
        request.setAttribute("caption", PageContextManager.getProperty("main.caption"));
        request.setAttribute("title", PageContextManager.getProperty("periodical.title"));
        request.setAttribute("frequency", PageContextManager.getProperty("periodical.frequency"));
        request.setAttribute("price", PageContextManager.getProperty("periodical.price"));
        request.setAttribute("description", PageContextManager.getProperty("periodical.description"));
        request.setAttribute("periodicalsList", periodicals);
    }
}
