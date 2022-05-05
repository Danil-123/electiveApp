package app.model.command.adminCommand.CourseCommand;

import app.model.command.Command;
import app.model.command.CourseLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditNameOfCourseCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        String name = request.getParameter("name");
        String newName = request.getParameter("newName");

        if (name != null && newName != null && CourseLogic.editCourse(name, newName)) {
            page = "/views/Success.jsp";
        } else {
            request.getSession().setAttribute("error", "something went wrong");
            page = "/error/error.jsp";
        }
        return page;
    }
}
