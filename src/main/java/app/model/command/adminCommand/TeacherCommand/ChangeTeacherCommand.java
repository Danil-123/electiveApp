package app.model.command.adminCommand.TeacherCommand;

import app.db.DBException;
import app.db.DBManager;
import app.entities.Course;
import app.entities.User;
import app.model.command.Command;
import app.model.command.CourseLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

public class ChangeTeacherCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ParseException, DBException {
        String id_course = request.getParameter("id_course");
        String id_account = request.getParameter("id_account");
        int id = Integer.parseInt(id_course);
        String page = null;
        if (CourseLogic.findTeacher(id_course)) {
            if (CourseLogic.setNewTeacher(id_course, id_account)) {
                Course course = CourseLogic.getCourse(id);
                User teacher = DBManager.getInstance().getTeacherName(id);
                List<User> students = CourseLogic.getAllStudents(id);

                List<User> teachers = CourseLogic.getAllTeacher();
                int countStudents = DBManager.getInstance().getNumOfStudents(id);
                if (course != null) {
                    System.out.println("teacher = "+teacher);
                    request.getSession().setAttribute("course", course);

                    request.getSession().setAttribute("teacher", teacher);

                    request.getSession().setAttribute("numOfStudent", countStudents);

                    request.getSession().setAttribute("students", students);

                    request.getSession().setAttribute("listOfTeacher", teachers);

                    page = "/views/detailOfCourse.jsp";
                }

            }
        } else if (CourseLogic.setTeacher(id_course, id_account)) {
            Course course = CourseLogic.getCourse(id);
            User teacher = DBManager.getInstance().getTeacherName(id);
            List<User> students = CourseLogic.getAllStudents(id);
            List<User> teachers = CourseLogic.getAllTeacher();
            int countStudents = DBManager.getInstance().getNumOfStudents(id);
            System.out.println("teacher = "+teacher);

            request.getSession().setAttribute("course", course);

            request.getSession().setAttribute("teacher", teacher);

            request.getSession().setAttribute("numOfStudent", countStudents);

            request.getSession().setAttribute("students", students);

            request.getSession().setAttribute("listOfTeacher", teachers);

            page = "/views/detailOfCourse.jsp";

        } else {
            request.getSession().setAttribute("error", "something went wrong");
            page = "/error/error.jsp";
        }

        return page;
    }
}
