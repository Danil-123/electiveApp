package app.model.command.registerCommand;

import app.model.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

public class GoToRegisterPage implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String page = "/views/registration.jsp";
        return page;
    }
}
