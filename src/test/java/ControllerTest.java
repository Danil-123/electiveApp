import app.Controller.Controller;
import app.model.command.Command;
import app.model.command.CommandEnum;
import app.model.command.loginCommand.LoginCommand;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

public class ControllerTest extends Assert {
    public static final String PATH = "/views/login.jsp";
    @Test
    public void controllerTest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        doNothing().when(session).setAttribute(isA(String.class), isA(String.class));
        when(request.getRequestDispatcher(PATH)).thenReturn(dispatcher);

        CommandEnum commandEnum = mock(CommandEnum.class);
        Controller controller = new Controller();
        try {
            when(request.getParameter("command"))
                    .thenReturn("login");
            when(commandEnum.getCurrentCommand()).thenReturn(new LoginCommand());

            controller.doGet(request, response);
            controller.doPost(request, response);
            verify(request, times(1)).getRequestDispatcher(PATH);
            verify(dispatcher).forward(request, response);

            Command command = commandEnum.getCurrentCommand();
            assertTrue(command.getClass() == LoginCommand.class);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
