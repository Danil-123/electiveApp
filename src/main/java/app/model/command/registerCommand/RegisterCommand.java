package app.model.command.registerCommand;


import app.db.DBException;
import app.db.DBManager;
import app.entities.User;
import app.model.command.Command;
import app.model.command.HashPass;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterCommand implements Command {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "psw";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_NAME = "name";
    private static final String PARAM_NAME_SURNAME = "surname";
    private static final String PARAM_NAME_PHONE = "phone";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        String name = request.getParameter(PARAM_NAME_NAME);
        String surname = request.getParameter(PARAM_NAME_SURNAME);
        String phone = request.getParameter(PARAM_NAME_PHONE);
        String role = request.getParameter("role");

        HttpSession session = request.getSession();
        if (login != null && RegisterLogic.checkLogin(login)) {
            session.setAttribute("loginMess",
                    "This login is already taken");
            page = "/views/registration.jsp";
        }
        if (phone != null && RegisterLogic.checkPhone(phone)) {
            session.setAttribute("phoneMess",
                    "This phone does not available");
            page = "/views/registration.jsp";
        }
        if (phone != null && RegisterLogic.chekEmail(email)) {
            session.setAttribute("emailMess",
                    "This email is already taken");
            page = "/views/registration.jsp";
        }
        if (page != null) {
            return page;
        }
        if (role == null) {
            role = "STUDENT";
        }
        pass = HashPass.getHash(pass);
        User user = User.createUser(login, pass, email, name, surname, phone, role);
        if (user != null && RegisterLogic.createUser(user) == true) {
            user = DBManager.getInstance().findUser(user.getLogin());
            session.setAttribute("user", user);
            if (role == "STUDENT") {
                page = "/views/profile.jsp";
            } else {
                page = "/views/Success.jsp";
            }
            SendEmail.sendEmail(name,surname);
        } else {
            session.setAttribute("error",
                    "something went wrong");
            page = "/views/error.jsp";
        }
        return page;
    }
}
