package controller.commands.userCommands;

import controller.SessionContent;
import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;
import controller.resourceManager.PageContextManager;
import model.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class CartCommand implements ActionCommand {
    private static PeriodicalService service;
    private static SessionContent sessionContent;

    public CartCommand() {
        service = PeriodicalService.getInstance();
        sessionContent = SessionContent.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        setAttributes(request, session);
        Optional<String> page = Optional.of(ConfigurationManager.getProperty("path.page.cart"));

        return page.get();
    }

    private void setAttributes(HttpServletRequest request, HttpSession session) {
        request.setAttribute("main", PageContextManager.getProperty("main.menu"));
        request.setAttribute("logout", PageContextManager.getProperty("authorization.logout"));
        request.setAttribute("user_details", PageContextManager.getProperty("main.user_details"));
        request.setAttribute("hi", PageContextManager.getProperty("main.hi"));
        request.setAttribute("userLogin", session.getAttribute("userLogin"));
        request.setAttribute("caption", PageContextManager.getProperty("cart.caption"));
        request.setAttribute("title", PageContextManager.getProperty("periodical.title"));
        request.setAttribute("frequency", PageContextManager.getProperty("periodical.frequency"));
        request.setAttribute("price", PageContextManager.getProperty("periodical.price"));
        request.setAttribute("description", PageContextManager.getProperty("periodical.description"));
        request.setAttribute("cartList", sessionContent.getBuyList());
        request.setAttribute("summary", PageContextManager.getProperty("cart.summary"));
        long totalCost = service.countTotalPrice(sessionContent.getBuyList());
        request.setAttribute("total_cost", totalCost);
        session.setAttribute("total_cost", totalCost);
        request.setAttribute("checkout", PageContextManager.getProperty("cart.checkout"));
        request.setAttribute("delete", PageContextManager.getProperty("cart.delete"));
    }


}
