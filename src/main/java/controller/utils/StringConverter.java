package controller.utils;

import javax.servlet.http.HttpServletRequest;

public class UriConverter {

    private UriConverter() {
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
}
