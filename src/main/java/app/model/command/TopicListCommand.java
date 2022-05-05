package app.model.command;

import app.db.DBException;
import app.entities.Topic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TopicListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        String page = null;

        List<Topic> allTopic = CourseLogic.getAllTopic();
        if (allTopic != null) {
            request.getSession().setAttribute("allTopic", allTopic);
            page = "/views/TopicList.jsp";
        } else {
            request.getSession().setAttribute("error",
                    "No one topic.");
            page = "/error/error.jsp";
        }
        return page;
    }
}