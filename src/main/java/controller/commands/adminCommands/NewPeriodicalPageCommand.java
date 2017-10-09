package controller.commands.adminCommands;

import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class NewPeriodicalPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("currentPage", request.getRequestURI());
        Optional<String> page = Optional.of(ConfigurationManager.getProperty("path.page.new_periodical"));
        return page.get();
    }


}
