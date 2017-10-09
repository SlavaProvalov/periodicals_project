package controller.commands.userCommands;

import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;
import model.entity.Periodical;
import model.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.Set;

public class CartCommand implements ActionCommand {
    private static PeriodicalService service;


    public CartCommand() {
        service = PeriodicalService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        setAttributes(request, session);
        Optional<String> page = Optional.of(ConfigurationManager.getProperty("path.page.cart"));
        return page.get();
    }

    private void setAttributes(HttpServletRequest request, HttpSession session) {
        request.setAttribute("cartList", session.getAttribute("cart"));
        long totalCost = service.countTotalPrice((Set<Periodical>) session.getAttribute("cart"));
        session.setAttribute("total_cost", totalCost);
        session.setAttribute("currentPage", request.getRequestURI());
    }


}
