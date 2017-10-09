package controller.commands.userCommands;

import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;
import controller.utils.ErrorConstructor;
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
            page = Optional.of(ConfigurationManager.getProperty("path.page.user_details"));
        } catch (SQLException e) {
            e.printStackTrace(); // TODO: 09.10.2017 log
            page = Optional.of(ConfigurationManager.getProperty("path.page.error"));
            ErrorConstructor.fillErrorMessage(request,e,"path.servlet.main");
        }
        return page.get();
    }

    private void setAttributes(HttpServletRequest request, HttpSession session, Client client, List<Periodical> periodicals) {
        session.setAttribute("firstName", client.getFirstName());
        session.setAttribute("lastName", client.getLastName());
        session.setAttribute("email", client.getEmail());
        session.setAttribute("phoneNumber", client.getPhoneNumber());
        request.setAttribute("signUpDate", client.getRegistrationDate());
        request.setAttribute("periodicalsList", periodicals);
        session.setAttribute("currentPage", request.getRequestURI());

    }
}
