package controller.commands;

import controller.resourceManager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        Optional<String> page = Optional.of(ConfigurationManager.getProperty("path.servlet.welcome"));
        return page.get();
    }
}
