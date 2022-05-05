package app.model.command;

import app.db.DBException;
import app.db.DBManager;
import app.entities.User;
import app.model.command.loginCommand.LoginLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;

public class ChangeAvatarCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ParseException, DBException {
        String page = "";
        HttpSession session = request.getSession();
        String url = request.getParameter("address");
        String login = session.getAttribute("login").toString();
        if (DBManager.getInstance().setAvatar(url, login)) {
            User user = LoginLogic.checkLogin(login);
            session.setAttribute("user", user);
            page = "/views/profile.jsp";
        } else {
            session.setAttribute("errorLoginPassMessage",
                    "Sorry,failed to set avatar.");
            page = "/views/login.jsp";
        }
        return page;
    }
}
