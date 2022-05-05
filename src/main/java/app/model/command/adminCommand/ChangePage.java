package app.model.command.adminCommand;

import app.db.DBException;
import app.entities.User;
import app.model.command.Command;
import app.model.command.CourseLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

public class ChangePage implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ParseException, DBException {
        int firstrow = Integer.parseInt(request.getParameter("firstrow"));
        int rowcount = Integer.parseInt(request.getParameter("rowcount"));
        String page = request.getParameter("page");
        if (page.equals("next") || page.equals("Следующая")) {
            rowcount += 4;
        } else if (page.equals("previous") || page.equals("Предыдущая")) {
            rowcount -= 4;
        }
        List<User> students = CourseLogic.pageOfStudent(firstrow, rowcount);
        int numOfStudent = CourseLogic.numOfStudent();
        request.getSession().setAttribute("listOfStudent", students);
        request.getSession().setAttribute("firstrow", firstrow);
        request.getSession().setAttribute("rowcount", rowcount);
        if (numOfStudent - rowcount > 4) {
            request.getSession().setAttribute("next", true);
        }else {
            request.getSession().setAttribute("next", false);
        }

        if (rowcount >= 4) {
            request.getSession().setAttribute("prev", true);
        }else {
            request.getSession().setAttribute("prev", false);
        }

        String page_jsp = "/views/StudentListByAdmin.jsp";
        return page_jsp;
    }
}
