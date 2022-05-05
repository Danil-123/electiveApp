package app.model.command.adminCommand;

import app.db.DBException;
import app.entities.Topic;
import app.model.command.Command;
import app.model.command.CourseLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

public class DelTopicList implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ParseException, DBException {
        String page = null;

        List<Topic> allDelTopic = CourseLogic.getAllDelTopic();
        if (allDelTopic != null) {
            request.getSession().setAttribute("allDelTopic", allDelTopic);
            page = "/views/DelTopicList.jsp";
        } else {
            request.getSession().setAttribute("error", "something went wrong");
            page = "/error/error.jsp";
        }
        return page;
    }
}
