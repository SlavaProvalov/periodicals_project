package controller.commands.userCommands;

import controller.ContextStorage;
import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;
import controller.resourceManager.PageContextManager;
import model.entity.Periodical;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.Set;

public class DeleteFromCart implements ActionCommand {
    private static ContextStorage contextStorage;

    public DeleteFromCart() {
        contextStorage = ContextStorage.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("Item_id"));
        HttpSession session = request.getSession();
        Set<Periodical> cart = (Set<Periodical>) session.getAttribute("cart");
        cart.remove(contextStorage.getPeriodicals().get(id));
        request.getSession().setAttribute("cart", cart);
        request.getSession().setAttribute("items", cart.size() > 0 ?
                cart.size() : ((PageContextManager) session.getAttribute("pageContextManager")).getProperty("cart.empty"));
        Optional<String> page = Optional.of(ConfigurationManager.getProperty("path.servlet.cart"));
        return page.get();
    }
}
