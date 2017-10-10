package controller.utils;

import controller.resourceManager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ErrorConstructor {

    public static void fillErrorPage(HttpServletRequest request, SQLException exception, String redirectPage) {
        request.setAttribute("errorCode", exception.getErrorCode());
        request.setAttribute("errorPath", exception.getSQLState());
        request.setAttribute("errorRole", request.getSession().getAttribute("role"));
        request.setAttribute("errorMessage", exception.getMessage());
        request.setAttribute("redirectServlet", ConfigurationManager.getProperty(redirectPage));
    }
}
