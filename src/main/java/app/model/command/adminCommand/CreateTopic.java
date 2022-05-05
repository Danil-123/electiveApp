package app.model.command.adminCommand;

import app.db.DBException;
import app.entities.Topic;
import app.model.command.Command;
import app.model.command.CourseLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

public class CreateTopic implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        String page = null;
        String topicName = request.getParameter("topicName");
        if (CourseLogic.addTopic(topicName)) {
            List<Topic> allTopic = CourseLogic.getAllTopic();
            if (allTopic != null) {
                request.getSession().setAttribute("allTopic", allTopic);
                page = "/views/TopicList.jsp";
            }
        } else {
            request.getSession().setAttribute("error",
                    "something went wrong");
            page = "/views/error.jsp";
        }

        return page;
    }
}
