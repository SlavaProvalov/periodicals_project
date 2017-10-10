package controller.utils;

import javax.servlet.http.HttpServletRequest;

public class StringConverter {

    private StringConverter() {
    }

    public static String uriToForFilter(HttpServletRequest request) {
        return request.getRequestURI().toLowerCase().
                replaceAll(".*periodicals/", "").
                replaceAll(".*view/", "").
                replaceAll(".*include/", "").
                replaceAll("\\.jsp", "").
                replaceAll("[?].+", "");
    }

    public static String uriToAction(HttpServletRequest request) {
        return request.getRequestURI().toLowerCase().
                replaceAll(".*periodicals/", "").replaceAll("[?].+", "");
    }

    public static long stringPriceToLong(HttpServletRequest request) {
        return Long.parseLong(request.getParameter("price").replaceAll("\\.", ""));
    }
}
