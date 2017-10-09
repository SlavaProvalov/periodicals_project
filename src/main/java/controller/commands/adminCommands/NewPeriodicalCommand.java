package controller.commands.adminCommands;

import controller.ContextStorage;
import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;
import controller.resourceManager.MessageManager;
import controller.utils.ErrorConstructor;
import model.entity.Periodical;
import model.entity.builder.PeriodicalBuilder;
import model.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Optional;

public class NewPeriodicalCommand implements ActionCommand {
    private MessageManager messageManager;
    private static PeriodicalService service;
    private static ContextStorage contextStorage = ContextStorage.getInstance();

    public NewPeriodicalCommand() {
        service = PeriodicalService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        Optional<String> page = Optional.empty();
        HttpSession session = request.getSession();
        messageManager = (MessageManager) session.getAttribute("messageManager");
        try {
            String title = request.getParameter("title");
            int frequency = Integer.parseInt(request.getParameter("frequency"));
            long price = Long.parseLong(request.getParameter("price").replaceAll("\\.", ""));
            String ruDescription = request.getParameter("ruDescription");
            String enDescription = request.getParameter("enDescription");
            Periodical periodical = new PeriodicalBuilder()
                    .createNewPeriodical()
                    .setTitle(title)
                    .setPublicationFrequency(frequency)
                    .setSubscriptionPrice(price)
                    .setRuDescription(ruDescription)
                    .setEnDescription(enDescription)
                    .getPeriodical();
            service.createNewPeriodical(periodical);
            contextStorage.getPeriodicals().put(periodical.getId(), periodical);
            request.setAttribute("redirectServlet", ConfigurationManager.getProperty("path.servlet.main"));
            request.setAttribute("success", messageManager.getProperty("message.success.creationOfNewPeriodical"));
            request.setAttribute("redirect", messageManager.getProperty("message.success.redirect"));
            page = Optional.of(ConfigurationManager.getProperty("path.page.success"));
        } catch (SQLException e) {
            //// TODO: 01.10.2017 log
            page = Optional.of(ConfigurationManager.getProperty("path.page.error"));
            ErrorConstructor.fillErrorMessage(request,e,"path.servlet.newPeriodical");

        }
        return page.get();
    }


}
