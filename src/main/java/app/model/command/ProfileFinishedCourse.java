package app.model.command;

import app.db.DBException;
import app.db.DBManager;
import app.entities.Course;
import app.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileFinishedCourse implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        String page = null;
        int id_student = Integer.parseInt(request.getParameter("id_student"));
        int id_course = Integer.parseInt(request.getParameter("id_course"));
        Course course = CourseLogic.getCourse(id_course);
        User teacher = DBManager.getInstance().getTeacherName(id_course);
        int countStudents = DBManager.getInstance().getNumOfStudents(id_course);
       String mark = CourseLogic.findMarkOfCourse(id_student,id_course);
        if (course != null) {
            request.getSession().setAttribute("course", course);
            request.getSession().setAttribute("teacher", teacher);
            request.getSession().setAttribute("numOfStudent", countStudents);
            request.getSession().setAttribute("mark", mark);
            page = "/views/profileOfFinishedCourse.jsp";
        } else {
            request.getSession().setAttribute("error",
                    "No one topic.");
            page = "/error/error.jsp";
        }
        return page;
    }
}
