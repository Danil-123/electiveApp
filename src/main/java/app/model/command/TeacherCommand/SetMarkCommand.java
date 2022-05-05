package app.model.command.TeacherCommand;

import app.db.DBException;
import app.db.DBManager;
import app.entities.User;
import app.model.command.Command;
import app.model.command.CourseLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

public class SetMarkCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ParseException, DBException {
        HttpSession session = request.getSession();
        String page = null;
        String id_student = request.getParameter("id_student");
        String mark = request.getParameter("mark");
        String course_id = request.getParameter("id_course");
        if (DBManager.getInstance().setMark(id_student, mark, course_id)) {
            List<User> students = CourseLogic.getAllStudents(Integer.parseInt(course_id));
            session.setAttribute("students", students);
            session.setAttribute("course_id", course_id);
            page = "/views/journal.jsp";
        } else {
            session.setAttribute("error",
                    "No one course.");
            page = "/error/error.jsp";
        }
        return page;
    }
}