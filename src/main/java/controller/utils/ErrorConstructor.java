package controller.utils;

import controller.resourceManager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ErrorConstructor {

    public static void fillErrorMessage(HttpServletRequest request, SQLException exception, String redirectPage) {
        request.setAttribute("errorCode", exception.getErrorCode());
        request.setAttribute("errorState", exception.getSQLState());
        request.setAttribute("errorMessage", exception.getMessage());
        request.setAttribute("redirectServlet", ConfigurationManager.getProperty(redirectPage));
    }
}
