package controller.filter;

import controller.resourceManager.ConfigurationManager;
import model.entity.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(urlPatterns = {"/"},
        dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST},
        servletNames = {"Controller"})
public class SecurityFilter implements Filter {
    private static List<String> adminAllowedCommands;
    private static List<String> userAllowedCommands;
    private static List<String> guestAllowedCommands;
    private static String userRedirectPage;
    private static String guestRedirectPage;

    public SecurityFilter() {
        adminAllowedCommands = Arrays.asList("logout", "user_details", "new_periodical", "new_periodical_page", "main",
                "add_to_cart", "delete_from_cart", "cart", "order_page", "order", "order_confirm", "success_page");

        userAllowedCommands = Arrays.asList("logout", "user_details", "main", "add_to_cart", "delete_from_cart", "cart",
                "order_page", "order", "order_confirm", "success_page");

        guestAllowedCommands = Arrays.asList("welcome", "index", "login_page", "login", "signup_page", "signup",
                "success_page", "");
        userRedirectPage = "path.servlet.main";
        guestRedirectPage = "path.servlet.login_page";
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        Role role = (Role) session.getAttribute("role");
        if (Role.ADMIN.equals(role)) {
            allowedCommands(chain, httpRequest, httpResponse, adminAllowedCommands, userRedirectPage);
        } else if (Role.USER.equals(role)) {
            allowedCommands(chain, httpRequest, httpResponse, userAllowedCommands, userRedirectPage);
        } else {
            if (!Role.GUEST.equals(role)) {
                role = Role.GUEST;
                session.setAttribute("role", role);
            }
            allowedCommands(chain, httpRequest, httpResponse, guestAllowedCommands, guestRedirectPage);
        }
    }

    private void allowedCommands(FilterChain chain, HttpServletRequest httpRequest, HttpServletResponse httpResponse, List<String> allowedCommands, String redirectPage) throws IOException, ServletException {
        List<String> ac = allowedCommands;

        String action = httpRequest.getRequestURI().toLowerCase().replaceAll(".*periodicals/", "").replaceAll("[?].+", "");
        boolean contains = ac.contains(action);
        System.out.println(action);
        System.out.println(contains);
        if (ac.contains(httpRequest.getRequestURI().toLowerCase().replaceAll(".*periodicals/", "").replaceAll("[?].+", ""))) {
            chain.doFilter(httpRequest, httpResponse);
        } else {
            RequestDispatcher dispatcher = httpRequest.getServletContext().getRequestDispatcher(ConfigurationManager.getProperty(redirectPage));
            dispatcher.forward(httpRequest, httpResponse);
        }
    }


    @Override
    public void destroy() {

    }
}
