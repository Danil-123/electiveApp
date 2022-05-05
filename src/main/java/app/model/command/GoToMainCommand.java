package app.model.command;

import app.db.DBException;
import app.entities.User;
import app.model.command.loginCommand.LoginLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoToMainCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        String page = null;
// извлечение из сессии
        String login = String.valueOf(request.getSession().getAttribute("login"));
        String pass = String.valueOf(request.getSession().getAttribute("pass"));
// проверка логина и пароля
        if (login == null || pass == null) {
            request.getSession().setAttribute("errorLoginPassMessage",
                    "Incorrect login or password.");
            page = "/views/login.jsp";
            return page;
        }
        User user = LoginLogic.checkLogin(login);
        if (user != null && pass.equals(user.getPassword())) {
            request.getSession().setAttribute("user", user);
// определение пути к main.jsp
            page = "/views/profile.jsp";
        } else {
            request.getSession().setAttribute("errorLoginPassMessage",
                    "Incorrect login or password.");
            page = "/views/login.jsp";
        }
        return page;
    }
}