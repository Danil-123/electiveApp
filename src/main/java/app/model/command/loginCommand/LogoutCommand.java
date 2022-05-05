package app.model.command.loginCommand;



import app.model.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = "/index.jsp";
// уничтожение сессии
        request.getSession().invalidate();
        return page;
    }
}