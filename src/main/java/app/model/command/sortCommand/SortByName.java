package app.model.command.sortCommand;

import app.db.DBException;
import app.entities.Course;
import app.model.command.Command;
import app.model.command.CourseLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByName implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {

        String page = null;
        String name = request.getSession().getAttribute("name").toString();

        if (name != null) {
            List<Course> courses = CourseLogic.getCoursesByTopic(name);
            Collections.sort(courses, Comparator.comparing(Course::getCourse_name));
            request.getSession().setAttribute("listOfCourses", courses);
            page = "/views/AllCourses.jsp";
        } else {
            request.getSession().setAttribute("error",
                    "No one course.");
            page = "/error/error.jsp";
        }
        return page;
    }
}
