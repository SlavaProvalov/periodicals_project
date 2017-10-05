package controller.commands.adminCommands;

import controller.commands.ActionCommand;
import controller.resourceManager.ConfigurationManager;
import controller.resourceManager.PageContextManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class NewPeriodicalPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        setAttributes(request, session);
        Optional<String> page = Optional.of(ConfigurationManager.getProperty("path.page.newPeriodical"));
        return page.get();
    }

    private void setAttributes(HttpServletRequest request, HttpSession session) {
        request.setAttribute("logout", PageContextManager.getProperty("authorization.logout"));
        request.setAttribute("main", PageContextManager.getProperty("main.menu"));
        request.setAttribute("hi", PageContextManager.getProperty("main.hi"));
        request.setAttribute("userLogin", session.getAttribute("userLogin"));

        request.setAttribute("user_details", PageContextManager.getProperty("main.user_details"));
        request.setAttribute("title", PageContextManager.getProperty("newPeriodical.title"));
        request.setAttribute("frequency", PageContextManager.getProperty("newPeriodical.frequency"));
        request.setAttribute("price", PageContextManager.getProperty("newPeriodical.price"));
        request.setAttribute("ruDescription", PageContextManager.getProperty("newPeriodical.ruDescription"));
        request.setAttribute("enDescription", PageContextManager.getProperty("newPeriodical.enDescription"));
        request.setAttribute("submit", PageContextManager.getProperty("newPeriodical.submit"));
        request.setAttribute("pattern_title", PageContextManager.getProperty("pattern.title"));
        request.setAttribute("pattern_price", PageContextManager.getProperty("pattern.price"));
    }
}
