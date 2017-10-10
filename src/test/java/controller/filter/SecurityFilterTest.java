package controller.filter;


import controller.resourceManager.ConfigurationManager;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecurityFilterTest extends Mockito {
    private SecurityFilter securityFilter = new SecurityFilter();


    @Test
    public void doFilterNormal() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        FilterChain chain = mock(FilterChain.class);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestURI()).thenReturn("/periodicals/welcome");
        when(request.getSession().getAttribute("role")).thenReturn("GUEST");
        securityFilter.doFilter(request, response, chain);
        verify(chain).doFilter(request, response);
    }

    @Test
    public void doFilterWrongAction() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        FilterChain chain = mock(FilterChain.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext context = mock(ServletContext.class);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestURI()).thenReturn("/periodicals/main");
        when(request.getSession().getAttribute("role")).thenReturn("GUEST");
        when(request.getServletContext()).thenReturn(context);
        when(request.getServletContext().getRequestDispatcher(ConfigurationManager.getProperty("path.servlet.error")))
                .thenReturn(dispatcher);
        securityFilter.doFilter(request, response, chain);
        verify(dispatcher).forward(request, response);
    }
}