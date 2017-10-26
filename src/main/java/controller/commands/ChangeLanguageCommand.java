package controller.commands;

import controller.resourceManager.MessageManager;
import controller.resourceManager.PageContextManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ChangeLanguageCommand implements ActionCommand {
    private static ChangeLanguageCommand instance;

    private ChangeLanguageCommand() {
    }

    public static ChangeLanguageCommand getInstance() {
        if (instance == null) {
            instance = new ChangeLanguageCommand();
        }
        return instance;
    }

    @Override
    public Optional<String> execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        PageContextManager pageContextManager = (PageContextManager) session.getAttribute("pageContextManager");
        MessageManager messageManager = (MessageManager) session.getAttribute("messageManager");
        String language = request.getParameter("language");
        pageContextManager.changeLocale(language);
        messageManager.changeLocale(language);
        session.setAttribute("pageContextManager", pageContextManager);
        session.setAttribute("messageManager", messageManager);
        session.setAttribute("language", language);
        session.setAttribute("curr_type", pageContextManager.getProperty("welcome.curr_type"));
        request.getSession().setAttribute("curr_rate", pageContextManager.getProperty("welcome.curr_rate"));
        Optional<String> page = Optional.of(request.getSession().getAttribute("currentPage").toString());
        return page;
    }
}
