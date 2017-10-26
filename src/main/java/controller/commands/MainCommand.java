package controller.commands;

import controller.ContextStorage;
import controller.resourceManager.ConfigurationManager;
import controller.resourceManager.PageContextManager;
import controller.utils.ErrorConstructor;
import model.entity.Periodical;
import model.service.PeriodicalService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MainCommand implements ActionCommand {
    private static MainCommand instance;
    private static Logger log;
    private static PeriodicalService service;
    private static ContextStorage contextStorage;


    private MainCommand() {
        service = PeriodicalService.getInstance();
        contextStorage = ContextStorage.getInstance();
        log = Logger.getLogger(MainCommand.class);
    }

    public static MainCommand getInstance() {
        if (instance == null) {
            instance = new MainCommand();
        }
        return instance;
    }

    @Override
    public Optional<String> execute(HttpServletRequest request) {
        Optional<String> page;
        HttpSession session = request.getSession();
        PageContextManager pageContextManager = (PageContextManager) session.getAttribute("pageContextManager");
        try {
            List<Periodical> userPeriodicals;
            setAttributes(request, session, pageContextManager);
            userPeriodicals = service.getPeriodicalsByClientId((int) request.getSession().getAttribute("userId"));
            session.setAttribute("userPeriodicals", userPeriodicals);
            if (contextStorage.getPeriodicals().isEmpty()) {

                for (Periodical periodical : service.getAllPeriodicals()) {
                    contextStorage.getPeriodicals().put(periodical.getId(), periodical);
                }
            }
            page = Optional.of(ConfigurationManager.getProperty("path.page.main"));
        } catch (SQLException e) {
            log.error(e);
            page = Optional.of(ConfigurationManager.getProperty("path.page.error"));
            ErrorConstructor.fillErrorPage(request, e, "path.servlet.logout");
        }
        return page;
    }

    private void setAttributes(HttpServletRequest request, HttpSession session, PageContextManager pageContextManager) throws SQLException {
        session.setAttribute("cartEmpty", pageContextManager.getProperty("cart.empty"));
        session.setAttribute("curr_type", pageContextManager.getProperty("welcome.curr_type"));
        session.setAttribute("curr_rate", pageContextManager.getProperty("welcome.curr_rate"));
        request.setAttribute("userLogin", session.getAttribute("userLogin"));
        request.setAttribute("items", session.getAttribute("items"));
        request.setAttribute("periodicalsList", contextStorage.getPeriodicals().values());
        session.setAttribute("currentPage", request.getRequestURI());

    }

}
