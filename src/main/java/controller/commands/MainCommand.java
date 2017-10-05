package controller.commands;

import controller.SessionContent;
import controller.resourceManager.ConfigurationManager;
import controller.resourceManager.PageContextManager;
import model.entity.Periodical;
import model.entity.Role;
import model.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Optional;

public class MainCommand implements ActionCommand {
    private static PeriodicalService service;
    private static SessionContent sessionContent;

    public MainCommand() {
        service = PeriodicalService.getInstance();
        sessionContent = SessionContent.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        Optional<String> page = Optional.empty();
        HttpSession session = request.getSession();
        try {
            if (request.getAttribute("items") != null) {
                request.setAttribute("items", PageContextManager.getProperty("cart.empty"));
            }
            setAttributes(request, session);
//            List<Periodical> userPeriodicals = service.getPeriodicalsByClientId((int) request.getSession().getAttribute("userId"));
//            if (!userPeriodicals.isEmpty()) {
//                sessionContent.getUserPeriodicals().addAll(userPeriodicals);
//            }
            for (Periodical periodical : service.getAllPeriodicals()) {
                sessionContent.getPeriodicals().put(periodical.getId(), periodical);
            }
            page = Optional.of(ConfigurationManager.getProperty("path.page.main"));
        } catch (SQLException e) {
            // TODO: 02.10.2017 log
            e.printStackTrace();
        }

        return page.get();
    }

    private void setAttributes(HttpServletRequest request, HttpSession session) throws SQLException {
        request.setAttribute("logout", PageContextManager.getProperty("authorization.logout"));
        request.setAttribute("cart", PageContextManager.getProperty("main.cart"));
        request.setAttribute("hi", PageContextManager.getProperty("main.hi"));
        request.setAttribute("userLogin", session.getAttribute("userLogin"));
        if (Role.ADMIN.equals((Role) request.getSession().getAttribute("userRole"))) {
            request.setAttribute("newPeriodical", PageContextManager.getProperty("main.newPeriodical"));
        }
        request.setAttribute("user_details", PageContextManager.getProperty("main.user_details"));
        request.setAttribute("items", session.getAttribute("items"));
        request.setAttribute("caption", PageContextManager.getProperty("main.caption"));
        request.setAttribute("title", PageContextManager.getProperty("periodical.title"));
        request.setAttribute("frequency", PageContextManager.getProperty("periodical.frequency"));
        request.setAttribute("price", PageContextManager.getProperty("periodical.price"));
        request.setAttribute("description", PageContextManager.getProperty("periodical.description"));
        request.setAttribute("periodicalsList", sessionContent.getPeriodicals().values());
        request.setAttribute("add", PageContextManager.getProperty("main.addToCart"));

    }

}
