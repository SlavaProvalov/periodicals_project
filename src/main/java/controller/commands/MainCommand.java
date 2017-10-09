package controller.commands;

import controller.ContextStorage;
import controller.resourceManager.ConfigurationManager;
import controller.resourceManager.PageContextManager;
import controller.utils.ErrorConstructor;
import model.entity.Periodical;
import model.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MainCommand implements ActionCommand {
    private static PeriodicalService service;
    private static ContextStorage contextStorage;
    private PageContextManager pageContextManager;

    public MainCommand() {
        service = PeriodicalService.getInstance();
        contextStorage = ContextStorage.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        Optional<String> page = Optional.empty();
        HttpSession session = request.getSession();
        pageContextManager = (PageContextManager) session.getAttribute("pageContextManager");
        try {
            List<Periodical> userPeriodicals;
            setAttributes(request, session);
            userPeriodicals = service.getPeriodicalsByClientId((int) request.getSession().getAttribute("userId"));
            session.setAttribute("userPeriodicals", userPeriodicals);
            if (contextStorage.getPeriodicals().isEmpty()) {

                for (Periodical periodical : service.getAllPeriodicals()) {
                    contextStorage.getPeriodicals().put(periodical.getId(), periodical);
                }
            }
            page = Optional.of(ConfigurationManager.getProperty("path.page.main"));
        } catch (SQLException e) {
            // TODO: 02.10.2017 log
            ErrorConstructor.fillErrorMessage(request, e, "path.servlet.logout");
        }
        return page.get();
    }

    private void setAttributes(HttpServletRequest request, HttpSession session) throws SQLException {
        session.setAttribute("cartEmpty", pageContextManager.getProperty("cart.empty"));
        session.setAttribute("curr_type", pageContextManager.getProperty("welcome.curr_type"));
        session.setAttribute("curr_rate", pageContextManager.getProperty("welcome.curr_rate"));
        request.setAttribute("userLogin", session.getAttribute("userLogin"));
        request.setAttribute("items", session.getAttribute("items"));
        request.setAttribute("periodicalsList", contextStorage.getPeriodicals().values());
        session.setAttribute("currentPage", request.getRequestURI());

    }

}
