package app.model.command;

import app.db.DBException;
import app.db.DBManager;
import app.entities.Course;
import app.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileCourseCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        String page = null;
        int id = Integer.parseInt(request.getParameter("id"));
        Course course = CourseLogic.getCourse(id);
        User teacher = null;
        try {
           teacher = DBManager.getInstance().getTeacherName(id);
       }catch (NullPointerException e){
           System.out.println("no teacher");
       }
        int countStudents = DBManager.getInstance().getNumOfStudents(id);
        if (course != null) {
            request.getSession().setAttribute("course", course);
            request.getSession().setAttribute("teacher", teacher);
            request.getSession().setAttribute("numOfStudent", countStudents);
            page = "/views/profileOfCourse.jsp";
        } else {
            request.getSession().setAttribute("error",
                    "No one topic.");
            page = "/error/error.jsp";
        }
        return page;
    }
}
