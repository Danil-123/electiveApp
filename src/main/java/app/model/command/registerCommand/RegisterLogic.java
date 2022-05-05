package app.model.command.registerCommand;

import app.db.DBManager;
import app.entities.User;

public class RegisterLogic {

    public static boolean createUser(User user) {
        return DBManager.getInstance().createUser(user);
    }

    public static boolean checkLogin(String login) {
        return DBManager.getInstance().chekLogin(login);
    }

    public static boolean checkPhone(String phone) {
        return DBManager.getInstance().chekPhone(phone);
    }

    public static boolean chekEmail(String email) {
        return DBManager.getInstance().chekEmail(email);
    }
}
