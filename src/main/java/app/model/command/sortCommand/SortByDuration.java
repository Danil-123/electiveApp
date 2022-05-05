package app.model.command.sortCommand;

import app.db.DBException;
import app.db.DBManager;
import app.entities.Course;
import app.model.command.Command;
import app.model.command.CourseLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

public class SortByDuration implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        HttpSession session = request.getSession();
        String page = null;
        String name = request.getSession().getAttribute("name").toString();
        if (name != null) {
            List<Course> courses = CourseLogic.getCoursesByTopic(name);
            for (int i = 0; i < courses.size(); i++) {
                String duration = DBManager.getInstance().getDuration(courses.get(i));
                courses.get(i).setDuration(duration);
            }
            Collections.sort(courses, (o1, o2) -> -o1.getDuration().compareTo(o2.getDuration()));
            session.setAttribute("listOfCourses", courses);
            page = "/views/AllCourses.jsp";
        } else {
            session.setAttribute("error",
                    "No one course.");
            page = "/error/error.jsp";
        }
        return page;
    }
}
