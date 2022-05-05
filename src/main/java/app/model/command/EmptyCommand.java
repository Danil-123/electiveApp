package app.model.command;

import app.model.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmptyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        /* в случае ошибки или прямого обращения к контроллеру
         * переадресация на страницу ввода логина */
        String page = "/views/login.jsp";
        return page;
    }
}
