package controller.commands;

import controller.resourceManager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class EmptyCommand implements ActionCommand {
    private static EmptyCommand instance;

    private EmptyCommand() {
    }

    public static EmptyCommand getInstance() {
        if (instance == null) {
            instance = new EmptyCommand();
        }
        return instance;
    }

    @Override
    public Optional<String> execute(HttpServletRequest request) {
        Optional<String> page = Optional.of(ConfigurationManager.getProperty("path.page.index"));
        return page;
    }
}
