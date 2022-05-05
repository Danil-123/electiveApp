package app.model.command.TeacherCommand;

import app.db.DBException;
import app.entities.User;
import app.model.command.Command;
import app.model.command.CourseLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

public class GoToJournal implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ParseException, DBException {
        String page = null;
        HttpSession session = request.getSession();
        String course_id = request.getParameter("course_id");
        List<User> students = CourseLogic.getAllStudents(Integer.parseInt(course_id));
        if (students != null) {
            session.setAttribute("students", students);
            session.setAttribute("course_id", course_id);
            page = "/views/journal.jsp";
        } else {
            session.setAttribute("error",
                    "No one topic.");
            page = "/error/error.jsp";
        }
        return page;
    }
}
