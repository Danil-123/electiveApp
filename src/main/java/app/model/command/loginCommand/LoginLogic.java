package app.model.command.loginCommand;


import app.db.DBException;
import app.db.DBManager;
import app.entities.User;

public class LoginLogic {

    public static User checkLogin(String enterLogin) throws DBException {
        return DBManager.getInstance().findUser(enterLogin);
    }
}