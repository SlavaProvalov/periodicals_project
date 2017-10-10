package controller.commands.authorizationCommands;

import controller.resourceManager.ConfigurationManager;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;


public class LoginCommandTest extends Mockito {

    LoginCommand command = new LoginCommand();

    private HttpServletRequest getHttpServletRequest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession(true)).thenReturn(session);
        return request;
    }

    @Test
    public void execute_normal() throws Exception {
        HttpServletRequest request = getHttpServletRequest();
        when(request.getParameter("login")).thenReturn("prov");
        when(request.getParameter("password")).thenReturn("7532159");

        assertEquals(ConfigurationManager.getProperty("path.servlet.main"), command.execute(request));
    }


    @Test
    public void execute_withLoginError() throws Exception {
        HttpServletRequest request = getHttpServletRequest();
        when(request.getParameter("login")).thenReturn("qwerty_123");
        when(request.getParameter("password")).thenReturn("1234567");
        assertEquals(ConfigurationManager.getProperty("path.servlet.login_page"), command.execute(request));
    }

    @Test
    public void execute_withWrongPassword() throws Exception {
        HttpServletRequest request = getHttpServletRequest();
        when(request.getParameter("login")).thenReturn("prov");
        when(request.getParameter("password")).thenReturn("1234567");
        assertEquals(ConfigurationManager.getProperty("path.servlet.login_page"), command.execute(request));
    }

    @Test(expected = NoSuchElementException.class)
    public void execute_withNull() throws Exception {
        HttpServletRequest request = getHttpServletRequest();
        when(request.getParameter("login")).thenReturn(null);
        when(request.getParameter("password")).thenReturn(null);
        command.execute(request);
    }

    @Test
    public void execute_withEmptyValues() throws Exception {
        HttpServletRequest request = getHttpServletRequest();
        when(request.getParameter("login")).thenReturn("");
        when(request.getParameter("password")).thenReturn("");
        assertEquals(command.execute(request), ConfigurationManager.getProperty("path.servlet.login_page"));
    }
}