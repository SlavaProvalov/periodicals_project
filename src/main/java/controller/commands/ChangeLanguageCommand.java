package controller.commands;

import controller.resourceManager.MessageManager;
import controller.resourceManager.PageContextManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ChangeLanguageCommand implements ActionCommand {
    PageContextManager pageContextManager;
    MessageManager messageManager;

    public ChangeLanguageCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        pageContextManager = (PageContextManager) session.getAttribute("pageContextManager");
        messageManager = (MessageManager) session.getAttribute("messageManager");
        String language = request.getParameter("language");
        pageContextManager.changeLocale(language);
        messageManager.changeLocale(language);
        session.setAttribute("pageContextManager", pageContextManager);
        session.setAttribute("messageManager", messageManager);
        session.setAttribute("language", language);
        session.setAttribute("curr_type", pageContextManager.getProperty("welcome.curr_type"));
        request.getSession().setAttribute("curr_rate", pageContextManager.getProperty("welcome.curr_rate"));
        Optional<String> page = Optional.of(request.getSession().getAttribute("currentPage").toString());
        return page.get();
    }
}
