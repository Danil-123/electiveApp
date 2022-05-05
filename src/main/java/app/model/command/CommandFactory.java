package app.model.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommandFactory {
    public Command defineCommand(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Command current = new EmptyCommand();
        // извлечение имени команды из запроса
        String action = request.getParameter("command");
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        if (name != null) {
            session.setAttribute("name", name);
        }

        if (id != null) {
            session.setAttribute("id", id);
        }

        if (action == null || action.isEmpty()) {
            // если команда не задана в текущем запросе
            return current;
        }
        // получение объекта, соответствующего команде
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            session.setAttribute("wrongAction", action
                    + ": command not found or wrong!");
        }
        return current;
    }
}