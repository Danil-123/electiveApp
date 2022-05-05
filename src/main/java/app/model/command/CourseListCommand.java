package app.model.command;

import app.db.DBException;
import app.entities.Course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CourseListCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        String page = null;
        String name = request.getParameter("name");


        if (name != null) {
            request.getSession().setAttribute("name", name);
            List<Course> courses = CourseLogic.getCoursesByTopic(name);
            request.getSession().setAttribute("listOfCourses", courses);
            request.getSession().setAttribute("topic", name);
            page = "/views/AllCourses.jsp";
        } else {
            request.getSession().setAttribute("error",
                    "No one course.");
            page = "/error/error.jsp";
        }
        return page;
    }
}
