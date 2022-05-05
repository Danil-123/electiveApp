package app.model.command.adminCommand.CourseCommand;

import app.db.DBException;
import app.entities.Course;
import app.model.command.Command;
import app.model.command.CourseLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;


public class CreateCourse implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ParseException, DBException {
        HttpSession session = request.getSession();
        String page = null;
        String topicName = request.getParameter("topicName");
        String courseName = request.getParameter("courseName");
        String start_day = request.getParameter("start_day");
        if (!CourseLogic.checkCourse(courseName)) {
            if (CourseLogic.createCourse(courseName, topicName, start_day)) {
                session.setAttribute("name", topicName);
                List<Course> courses = CourseLogic.getCoursesByTopic(topicName);
                session.setAttribute("listOfCourses", courses);
                session.setAttribute("topic", topicName);
                page = "/views/AllCourses.jsp";
            } else {
                session.setAttribute("error",
                        "this course has already exist");
                page = "/views/error.jsp";
            }
        } else if (CourseLogic.createCourseTheSameName(courseName, topicName, start_day)) {
            session.setAttribute("name", topicName);
            List<Course> courses = CourseLogic.getCoursesByTopic(topicName);
            session.setAttribute("listOfCourses", courses);
            session.setAttribute("topic", topicName);
            page = "/views/AllCourses.jsp";

        } else {
            session.setAttribute("error",
                    "this course has already exist");
            page = "/views/error.jsp";
        }

        return page;
    }
}
