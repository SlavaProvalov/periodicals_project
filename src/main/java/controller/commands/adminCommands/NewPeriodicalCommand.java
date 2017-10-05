package controller.commands.adminCommands;

import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;
import controller.resourceManager.MessageManager;
import model.entity.builder.PeriodicalBuilder;
import model.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Optional;

public class NewPeriodicalCommand implements ActionCommand {

    private static PeriodicalService service;

    public NewPeriodicalCommand() {
        service = PeriodicalService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        Optional<String> page = Optional.empty();
        try {
            String title = request.getParameter("title");
            int frequency = Integer.parseInt(request.getParameter("frequency"));
            long price = Long.parseLong(request.getParameter("price"));
            String ruDescription = request.getParameter("ruDescription");
            String enDescription = request.getParameter("enDescription");
            service.createNewPeriodical(PeriodicalBuilder.createNewPeriodical(title, frequency,
                    price, ruDescription, enDescription));
            page = Optional.of(ConfigurationManager.getProperty("path.page.success"));
            request.setAttribute("redirectServlet", ConfigurationManager.getProperty("path.servlet.main"));
            request.setAttribute("success", MessageManager.getProperty("message.success.creationOfNewPeriodical"));
            request.setAttribute("redirect", MessageManager.getProperty("message.success.redirect"));
        } catch (SQLException e) {
            //// TODO: 01.10.2017 log
        }
        return page.get();
    }


}
