package controller.commands;

import controller.resourceManager.ConfigurationManager;
import controller.resourceManager.PageContextManager;
import controller.utils.ErrorConstructor;
import model.service.PeriodicalService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Optional;

public class WelcomeCommand implements ActionCommand {
    private static WelcomeCommand instance;
    private static Logger log;
    private static PeriodicalService service;

    private WelcomeCommand() {
        service = PeriodicalService.getInstance();
        log = Logger.getLogger(WelcomeCommand.class);
    }

    public static WelcomeCommand getInstance() {
        if (instance == null) {
            instance = new WelcomeCommand();
        }
        return instance;
    }

    @Override
    public Optional<String> execute(HttpServletRequest request) {

        Optional<String> page;
        HttpSession session = request.getSession(true);
        PageContextManager pageContextManager = (PageContextManager) session.getAttribute("pageContextManager");
        try {
            request.setAttribute("periodicalsList", service.getAllPeriodicals());
            session.setAttribute("curr_type", pageContextManager.getProperty("welcome.curr_type"));
            session.setAttribute("curr_rate", pageContextManager.getProperty("welcome.curr_rate"));
            session.setAttribute("guest", pageContextManager.getProperty("header.guest"));
            session.setAttribute("currentPage", ConfigurationManager.getProperty("path.servlet.welcome"));
            page = Optional.of(ConfigurationManager.getProperty("path.page.welcome"));
        } catch (SQLException e) {
            log.error(e);
            page = Optional.of(ConfigurationManager.getProperty("path.page.error"));
            ErrorConstructor.fillErrorPage(request, e, "path.page.index");
        }
        return page;
    }

}
