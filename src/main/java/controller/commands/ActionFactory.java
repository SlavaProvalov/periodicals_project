package controller.commands;

import controller.resourceManager.MessageManager;

import javax.servlet.http.HttpServletRequest;

public  class ActionFactory {
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        String action = request.getRequestURI().toLowerCase().replaceAll(".*periodicals/", "").replaceAll("[?].+", "");// // TODO: 05.10.2017
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongAction"));
        }
        return current;
    }
}
