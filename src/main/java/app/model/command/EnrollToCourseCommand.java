package app.model.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EnrollToCourseCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("EnrollToCourseCommand");
        String page = null;
        int course_id = Integer.parseInt(request.getParameter("course_id"));
        int student_id = Integer.parseInt(request.getParameter("student_id"));
        if (CourseLogic.addStudentToCourse(course_id, student_id)) {
            page = "/views/Success.jsp";
        } else {
            request.getSession().setAttribute("mess",
                    "You are already enrolled in this course");
            page = "/views/unSuccess.jsp";
        }
        return page;
    }
}
