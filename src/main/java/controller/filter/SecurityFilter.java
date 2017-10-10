package controller.filter;

import controller.resourceManager.ConfigurationManager;
import controller.resourceManager.MessageManager;
import controller.resourceManager.PageContextManager;
import controller.utils.StringConverter;
import org.apache.log4j.Logger;

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
    private static final Logger log = Logger.getLogger(SecurityFilter.class);
    private static List<String> adminAllowedCommands;
    private static List<String> userAllowedCommands;
    private static List<String> guestAllowedCommands;
    private static String errorPage;

    public SecurityFilter() {
        adminAllowedCommands = Arrays.asList("logout", "user_details", "user_update",
                "user_update_page", "new_periodical", "check_all_orders",
                "new_periodical_page", "main", "add_to_cart",
                "delete_from_cart", "cart", "order_page",
                "order", "order_confirm", "success_page",
                "change_language", "error", "");

        userAllowedCommands = Arrays.asList("logout", "user_details", "user_update",
                "user_update_page", "main",
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
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        String role = ((String) session.getAttribute("role"));
        if (ADMIN.name().equals(role)) {
            allowedCommands(chain, httpRequest, httpResponse, adminAllowedCommands);
        } else if (USER.name().equals(role)) {
            allowedCommands(chain, httpRequest, httpResponse, userAllowedCommands);
        } else {
            if (!GUEST.name().equals(role)) {
                initDefault(session);
            }
            allowedCommands(chain, httpRequest, httpResponse, guestAllowedCommands);
        }
    }

    private void initDefault(HttpSession session) {
        session.setAttribute("role", GUEST.name());
        session.setAttribute("language", "en");
        session.setAttribute("pageContextManager", new PageContextManager("en"));
        session.setAttribute("messageManager", new MessageManager("en"));
    }

    private String getRedirectPage(HttpServletRequest request) {
        return request.getSession().getAttribute("role").equals(GUEST.name()) ? "path.servlet.welcome" : "path.servlet.main";
    }

    private void allowedCommands(FilterChain chain, HttpServletRequest request, HttpServletResponse response,
                                 List<String> allowedCommands) throws IOException, ServletException {
        List<String> ac = allowedCommands;
        if (ac.contains(StringConverter.uriToForFilter(request))) {
            chain.doFilter(request, response);
        } else {
            log.warn("attempt of restricted access to: " + request.getRequestURI());
            request.setAttribute("redirectPage", getRedirectPage(request));
            request.setAttribute("restrictedPage", request.getRequestURI());
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher(ConfigurationManager.getProperty(errorPage));
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
