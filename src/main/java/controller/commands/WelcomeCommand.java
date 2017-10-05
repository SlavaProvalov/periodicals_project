package controller.commands;

import config.Localization;
import controller.resourceManager.ConfigurationManager;
import controller.resourceManager.PageContextManager;
import model.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;

public class WelcomeCommand implements ActionCommand {

    private static PeriodicalService service;

    public WelcomeCommand() {
        service = PeriodicalService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        Optional<String> page = Optional.empty();
        try {
            Class.forName("config.Localization");
            setAttributes(request);
            String language = request.getParameter("language");
            if (Optional.ofNullable(language).isPresent()) {
                Localization.getInstance().setLocale(new Locale(language));
            }
            request.getSession(true).setAttribute("language", language);
            page = Optional.of(ConfigurationManager.getProperty("path.page.welcome"));
        } catch (SQLException e) {
            e.printStackTrace();//TODO log
        } catch (ClassNotFoundException e) {

        }
        return page.get();
    }

    private void setAttributes(HttpServletRequest request) throws SQLException {
        request.setAttribute("login", PageContextManager.getProperty("authorization.login"));
        request.setAttribute("signUp", PageContextManager.getProperty("authorization.signUp"));
        request.setAttribute("caption", PageContextManager.getProperty("welcome.caption"));
        request.setAttribute("periodicalsList", service.getAllPeriodicals());
        request.setAttribute("title", PageContextManager.getProperty("periodical.title"));
        request.setAttribute("frequency", PageContextManager.getProperty("periodical.frequency"));
        request.setAttribute("price", PageContextManager.getProperty("periodical.price"));
        request.setAttribute("description", PageContextManager.getProperty("periodical.description"));
    }


}
