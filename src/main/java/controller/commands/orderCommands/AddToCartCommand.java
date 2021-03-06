package controller.commands.orderCommands;

import controller.ContextStorage;
import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;
import model.entity.Periodical;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class AddToCartCommand implements ActionCommand {
    private static AddToCartCommand instance;
    private static ContextStorage contextStorage;

    private AddToCartCommand() {
        contextStorage = ContextStorage.getInstance();
    }

    public static AddToCartCommand getInstance() {
        if (instance == null) {
            instance = new AddToCartCommand();
        }
        return instance;
    }

    @Override
    public Optional<String> execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int periodicalId = Integer.parseInt(request.getParameter("pItem_id"));
        List<Periodical> userPeriodicals = (List<Periodical>) session.getAttribute("userPeriodicals");
        if (!userPeriodicals.contains(contextStorage.getPeriodicals().get(periodicalId))) {
            Set<Periodical> cart = ((Set<Periodical>) session.getAttribute("cart"));
            cart.add(contextStorage.getPeriodicals().get(periodicalId));
            session.setAttribute("items", cart.size());
            session.setAttribute("cart", cart);
        }
        Optional<String> page = Optional.of(ConfigurationManager.getProperty("path.servlet.main"));
        return page;
    }


}
