package controller.commands;

import controller.resourceManager.MessageManager;
import controller.utils.UriConverter;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {


    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        String action = UriConverter.uriToAction(request);
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action + ((MessageManager) request.getSession()).getProperty("message.wrongAction"));
        }
        return current;
    }
}
