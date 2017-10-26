package controller.commands;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface ActionCommand {
    Optional<String> execute(HttpServletRequest request);
}
