package controller.filter;

import controller.resourceManager.ConfigurationManager;
import controller.resourceManager.MessageManager;
import controller.resourceManager.PageContextManager;
import controller.utils.UriConverter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static model.entity.Role.*;

@WebFilter(urlPatterns = {"/*"},
        dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST},
        servletNames = {"Controller"})
public class SecurityFilter implements Filter {
    private static List<String> adminAllowedCommands;
    private static List<String> userAllowedCommands;
    private static List<String> guestAllowedCommands;
    private static String errorPage;

    public SecurityFilter() {
        adminAllowedCommands = Arrays.asList("logout", "user_details", "user_update", "user_update_page", "new_periodical",
                "new_periodical_page", "main", "add_to_cart",
                "delete_from_cart", "cart", "order_page",
                "order", "order_confirm", "success_page",
                "change_language", "error", "");

        userAllowedCommands = Arrays.asList("logout", "user_details", "user_update", "user_update_page", "main",
                "add_to_cart", "delete_from_cart", "cart",
                "order_page", "order", "order_confirm",
                "success_page", "change_language", "error", "user_delete");

        guestAllowedCommands = Arrays.asList("welcome", "index", "login_page",
                "login", "signup_page", "signup",
                "success_page", "change_language", "", "error");
        errorPage = "path.servlet.error";

    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        String role = ((String) session.getAttribute("role"));
        if (ADMIN.name().equals(role)) {
            allowedCommands(chain, httpRequest, httpResponse, adminAllowedCommands, errorPage);
        } else if (USER.name().equals(role)) {
            allowedCommands(chain, httpRequest, httpResponse, userAllowedCommands, errorPage);
        } else {
            if (!GUEST.name().equals(role)) {
                initDefault(session, role);
            }
            allowedCommands(chain, httpRequest, httpResponse, guestAllowedCommands, errorPage);
        }
    }

    private void initDefault(HttpSession session, String role) {
        role = GUEST.name();
        session.setAttribute("role", role);
        session.setAttribute("language", "en");
        session.setAttribute("pageContextManager", new PageContextManager("en"));
        session.setAttribute("messageManager", new MessageManager("en"));
    }

    private void allowedCommands(FilterChain chain, HttpServletRequest httpRequest, HttpServletResponse httpResponse, List<String> allowedCommands, String redirectPage) throws IOException, ServletException {
        List<String> ac = allowedCommands;
//--------------DEBUG---------------------
//        String action = UriConverter.uriToForFilter(httpRequest);
//        boolean contains = ac.contains(action);
//        System.out.println(action);
//        System.out.println(contains);
//----------------------------------------
        if (ac.contains(UriConverter.uriToForFilter(httpRequest))) {
            chain.doFilter(httpRequest, httpResponse);
        } else {
            httpRequest.getSession().setAttribute("currentPage", httpRequest.getRequestURI());
            RequestDispatcher dispatcher = httpRequest.getServletContext().getRequestDispatcher(ConfigurationManager.getProperty(redirectPage));
            dispatcher.forward(httpRequest, httpResponse);
        }
    }


    @Override
    public void destroy() {

    }
}
