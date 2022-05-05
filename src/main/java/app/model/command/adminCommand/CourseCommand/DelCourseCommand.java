package app.model.command.adminCommand.CourseCommand;
import app.model.command.Command;
import app.model.command.CourseLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DelCourseCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        String id_course = request.getParameter("course_id");

        if (CourseLogic.delCourse(id_course)) {
            page = "/views/Success.jsp";
        } else {
            request.getSession().setAttribute("error", "something went wrong");
            page = "/error/error.jsp";
        }
        return page;
    }
}
