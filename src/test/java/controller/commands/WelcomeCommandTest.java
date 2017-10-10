package controller.commands;

import controller.resourceManager.ConfigurationManager;
import controller.resourceManager.PageContextManager;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;

public class WelcomeCommandTest extends Mockito {
    WelcomeCommand command = new WelcomeCommand();

    private HttpServletRequest getRequest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession(true)).thenReturn(session);
        PageContextManager manager = new PageContextManager("en");
        when(session.getAttribute("pageContextManager")).thenReturn(manager);
        return request;
    }

    @Test
    public void execute_normal() throws Exception {
        HttpServletRequest request = getRequest();
        assertEquals(ConfigurationManager.getProperty("path.page.welcome"), command.execute(request));
    }


}