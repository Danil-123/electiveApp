package app.model.command.adminCommand.StudentCommand;

import app.db.DBException;
import app.entities.User;
import app.model.command.Command;
import app.model.command.CourseLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

public class GoToProfileStudent implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ParseException, DBException {
        int firstrow = Integer.parseInt(request.getParameter("firstrow"));
        int rowcount = Integer.parseInt(request.getParameter("rowcount"));

        List<User> students = CourseLogic.pageOfStudent(firstrow, rowcount);
        request.getSession().setAttribute("listOfStudent", students);
        request.getSession().setAttribute("firstrow", firstrow);
        request.getSession().setAttribute("rowcount", rowcount);
        request.getSession().setAttribute("next",true);
        String page_jsp = "/views/StudentListByAdmin.jsp";
        return page_jsp;
    }
}