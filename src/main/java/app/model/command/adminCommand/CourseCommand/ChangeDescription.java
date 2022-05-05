package app.model.command.adminCommand.CourseCommand;

import app.db.DBException;
import app.db.DBManager;
import app.entities.Course;
import app.entities.User;
import app.model.command.Command;
import app.model.command.CourseLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

public class ChangeDescription implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ParseException, DBException {
        HttpSession session = request.getSession();
        String page = null;
        int id_course = Integer.parseInt(request.getParameter("id_course"));
        String description = request.getParameter("description");
        if (CourseLogic.findDescription(id_course)) {
            if (CourseLogic.changeDecsription(id_course, description)) {
                Course course = CourseLogic.getCourse(id_course);
                User teacher = DBManager.getInstance().getTeacherName(id_course);
                List<User> students = CourseLogic.getAllStudents(id_course);
                List<User> teachers = CourseLogic.getAllTeacher();
                int countStudents = DBManager.getInstance().getNumOfStudents(id_course);

                if (course != null) {
                    session.setAttribute("course", course);
                    session.setAttribute("teacher", teacher);
                    session.setAttribute("numOfStudent", countStudents);
                    session.setAttribute("students", students);
                    session.setAttribute("listOfTeacher", teachers);

                    page = "/views/detailOfCourse.jsp";
                }
            }
        } else if (CourseLogic.setDescription(id_course, description)) {
            Course course = CourseLogic.getCourse(id_course);
            User teacher = DBManager.getInstance().getTeacherName(id_course);
            List<User> students = CourseLogic.getAllStudents(id_course);
            List<User> teachers = CourseLogic.getAllTeacher();
            int countStudents = DBManager.getInstance().getNumOfStudents(id_course);

            if (course != null) {
                session.setAttribute("course", course);
                session.setAttribute("teacher", teacher);
                session.setAttribute("numOfStudent", countStudents);
                session.setAttribute("students", students);
                session.setAttribute("listOfTeacher", teachers);

                page = "/views/detailOfCourse.jsp";
            }
        } else {
            session.setAttribute("error", "something went wrong");
            page = "/error/error.jsp";
        }
        return page;
    }
}
