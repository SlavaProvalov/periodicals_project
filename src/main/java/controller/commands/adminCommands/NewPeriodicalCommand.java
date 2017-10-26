package controller.commands.adminCommands;

import controller.ContextStorage;
import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;
import controller.resourceManager.MessageManager;
import controller.utils.EntityBuilder;
import controller.utils.ErrorConstructor;
import model.entity.Periodical;
import model.service.PeriodicalService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Optional;

public class NewPeriodicalCommand implements ActionCommand {
    private static NewPeriodicalCommand instance;
    private static Logger log;
    private static PeriodicalService service;
    private static ContextStorage contextStorage;

    private NewPeriodicalCommand() {
        service = PeriodicalService.getInstance();
        contextStorage = ContextStorage.getInstance();
        log = Logger.getLogger(NewPeriodicalCommand.class);
    }

    public static NewPeriodicalCommand getInstance() {
        if (instance == null) {
            instance = new NewPeriodicalCommand();
        }
        return instance;
    }

    @Override
    public Optional<String> execute(HttpServletRequest request) {
        Optional<String> page;
        HttpSession session = request.getSession();
        MessageManager messageManager = (MessageManager) session.getAttribute("messageManager");
        try {
            Periodical periodical = EntityBuilder.createPeriodicalFromRequest(request);
            service.createNewPeriodical(periodical);
            contextStorage.getPeriodicals().put(periodical.getId(), periodical);
            request.setAttribute("redirectServlet", ConfigurationManager.getProperty("path.servlet.main"));
            request.setAttribute("success", messageManager.getProperty("message.success.creationOfNewPeriodical"));
            request.setAttribute("redirect", messageManager.getProperty("message.success.redirect"));
            page = Optional.of(ConfigurationManager.getProperty("path.page.success"));
        } catch (SQLException e) {
            log.error(e);
            page = Optional.of(ConfigurationManager.getProperty("path.page.error"));
            ErrorConstructor.fillErrorPage(request, e, "path.servlet.newPeriodical");
        }
        return page;
    }


}
