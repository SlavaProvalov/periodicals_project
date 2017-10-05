package controller.commands;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SuccessPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        Optional<String> page = Optional.empty();
        return page.get();
    }
}
