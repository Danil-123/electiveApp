package commandTest.adminCommandTest;

import app.db.DBException;
import app.db.DBManager;
import app.entities.Course;
import app.model.command.adminCommand.CourseCommand.ChangeDescription;
import app.model.command.adminCommand.CourseCommand.ChangeDuration;
import app.model.command.adminCommand.CourseCommand.CreateCourse;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.MockedStatic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import static app.db.SQLQueries.SQL_FIND_COURSES_BY_TOPIC;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

public class CourseCommandTest extends Assert {

    @Test
    public void changeDescriptionTest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("id_course")).thenReturn("1");
        when(request.getParameter("description")).thenReturn("description");
        doNothing().when(session).setAttribute(isA(String.class), isA(String.class));

        ResultSet rs = mock(ResultSet.class);
        Connection con = mock(Connection.class);
        PreparedStatement pstmt = mock(PreparedStatement.class);
        DBManager db = mock(DBManager.class);
        MockedStatic<DBManager> mocked =
                mockStatic(DBManager.class);

        try {
            when(pstmt.executeQuery()).thenReturn(rs);
            when(con.prepareStatement(isA(String.class))).thenReturn(pstmt);
            when(rs.next())
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(false)
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(false)
                    .thenReturn(true)
                    .thenReturn(false);
            when(db.checkDescription(1))
                    .thenReturn(true);
            when(db.changeDescription(isA(Integer.class), isA(String.class)))
                    .thenReturn(true);
            when(rs.getInt("id"))
                    .thenReturn(1);
            when(rs.getString("course_name"))
                    .thenReturn("course_name");
            when(rs.getString("course_topic"))
                    .thenReturn("course_topic");
            when(rs.getString("start_day"))
                    .thenReturn("start_day");
            when(rs.getString("course_info"))
                    .thenReturn("course_info");
            when(rs.getString("duration"))
                    .thenReturn("duration");
            when(rs.getInt("id_account"))
                    .thenReturn(1);
            when(rs.getString("password"))
                    .thenReturn("1946");
            when(rs.getString("login"))
                    .thenReturn("Nikolay");
            when(rs.getString("name"))
                    .thenReturn("Nikolay");
            when(rs.getString("surname"))
                    .thenReturn("Sergeevich");
            when(rs.getInt("id"))
                    .thenReturn(1);
            when(rs.getString("role"))
                    .thenReturn("role");
            when(rs.getString("block_status"))
                    .thenReturn("block_status");
            when(rs.getString("email"))
                    .thenReturn("email");
            when(rs.getString("telephone"))
                    .thenReturn("phone");
            when(rs.getString("student_id"))
                    .thenReturn("1");
            when(rs.getString("mark"))
                    .thenReturn("4");


            when(db.getConnection()).thenReturn(con);
            mocked.when(DBManager::getInstance)
                    .thenReturn(db);
            doCallRealMethod().when(db).checkDescription(1);
            doCallRealMethod().when(db).findCourse(1);
            doCallRealMethod().when(db).getNumOfStudents(1);

            Course course = db.findCourse(1);
            assertTrue(course != null);
            boolean actual = db.checkDescription(1);
            assertTrue(actual);

            int num_of_student = db.getNumOfStudents(1);
            assertEquals(2, num_of_student);

            ChangeDescription changeDescription = new ChangeDescription();
            String page = changeDescription.execute(request, response);
            assertEquals("/views/detailOfCourse.jsp", page);
        } catch (SQLException | ParseException | DBException e) {
            e.printStackTrace();
        }
        mocked.close();
    }

    @Test
    public void changeDescriptionTest2() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("id_course")).thenReturn("1");
        when(request.getParameter("description")).thenReturn("description");
        doNothing().when(session).setAttribute(isA(String.class), isA(String.class));

        ResultSet rs = mock(ResultSet.class);
        Connection con = mock(Connection.class);
        PreparedStatement pstmt = mock(PreparedStatement.class);
        DBManager db = mock(DBManager.class);
        MockedStatic<DBManager> mocked =
                mockStatic(DBManager.class);

        try {
            when(pstmt.executeQuery()).thenReturn(rs);
            when(con.prepareStatement(isA(String.class))).thenReturn(pstmt);
            when(rs.next())
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(false)
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(false)
                    .thenReturn(true)
                    .thenReturn(false);
            when(db.checkDescription(1))
                    .thenReturn(false);
            when(db.setDescription(isA(Integer.class), isA(String.class)))
                    .thenReturn(true);
            when(rs.getInt("id"))
                    .thenReturn(1);
            when(rs.getString("course_name"))
                    .thenReturn("course_name");
            when(rs.getString("course_topic"))
                    .thenReturn("course_topic");
            when(rs.getString("start_day"))
                    .thenReturn("start_day");
            when(rs.getString("course_info"))
                    .thenReturn("course_info");
            when(rs.getString("duration"))
                    .thenReturn("duration");
            when(rs.getInt("id_account"))
                    .thenReturn(1);
            when(rs.getString("password"))
                    .thenReturn("1946");
            when(rs.getString("login"))
                    .thenReturn("Nikolay");
            when(rs.getString("name"))
                    .thenReturn("Nikolay");
            when(rs.getString("surname"))
                    .thenReturn("Sergeevich");
            when(rs.getInt("id"))
                    .thenReturn(1);
            when(rs.getString("role"))
                    .thenReturn("role");
            when(rs.getString("block_status"))
                    .thenReturn("block_status");
            when(rs.getString("email"))
                    .thenReturn("email");
            when(rs.getString("telephone"))
                    .thenReturn("phone");
            when(rs.getString("student_id"))
                    .thenReturn("1");
            when(rs.getString("mark"))
                    .thenReturn("4");

            when(db.getConnection()).thenReturn(con);
            mocked.when(DBManager::getInstance)
                    .thenReturn(db);
            doCallRealMethod().when(db).setDescription(1, "description");
            doCallRealMethod().when(db).findCourse(1);
            doCallRealMethod().when(db).getNumOfStudents(1);

            Course course = db.findCourse(1);
            assertTrue(course != null);
            boolean actual = db.setDescription(1, "description");
            assertTrue(actual);

            int num_of_student = db.getNumOfStudents(1);
            assertEquals(3, num_of_student);

            ChangeDescription changeDescription = new ChangeDescription();
            String page = changeDescription.execute(request, response);
            assertEquals("/views/detailOfCourse.jsp", page);
        } catch (SQLException | ParseException | DBException e) {
            e.printStackTrace();
        }
        mocked.close();
    }

    @Test
    public void changeDurationTest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("id_course")).thenReturn("1");
        when(request.getParameter("duration")).thenReturn("duration");
        doNothing().when(session).setAttribute(isA(String.class), isA(String.class));

        ResultSet rs = mock(ResultSet.class);
        Connection con = mock(Connection.class);
        PreparedStatement pstmt = mock(PreparedStatement.class);
        DBManager db = mock(DBManager.class);
        MockedStatic<DBManager> mocked =
                mockStatic(DBManager.class);

        try {
            when(pstmt.executeQuery()).thenReturn(rs);
            when(con.prepareStatement(isA(String.class))).thenReturn(pstmt);
            when(rs.next())
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(false)
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(false)
                    .thenReturn(true)
                    .thenReturn(false);

            when(db.changeDuration("1", "duration"))
                    .thenReturn(true);
            when(rs.getInt("id"))
                    .thenReturn(1);
            when(rs.getString("course_name"))
                    .thenReturn("course_name");
            when(rs.getString("course_topic"))
                    .thenReturn("course_topic");
            when(rs.getString("start_day"))
                    .thenReturn("start_day");
            when(rs.getString("course_info"))
                    .thenReturn("course_info");
            when(rs.getString("duration"))
                    .thenReturn("duration");
            when(rs.getInt("id_account"))
                    .thenReturn(1);
            when(rs.getString("password"))
                    .thenReturn("1946");
            when(rs.getString("login"))
                    .thenReturn("Nikolay");
            when(rs.getString("name"))
                    .thenReturn("Nikolay");
            when(rs.getString("surname"))
                    .thenReturn("Sergeevich");
            when(rs.getInt("id"))
                    .thenReturn(1);
            when(rs.getString("role"))
                    .thenReturn("role");
            when(rs.getString("block_status"))
                    .thenReturn("block_status");
            when(rs.getString("email"))
                    .thenReturn("email");
            when(rs.getString("telephone"))
                    .thenReturn("phone");
            when(rs.getString("student_id"))
                    .thenReturn("1");
            when(rs.getString("mark"))
                    .thenReturn("4");

            when(db.getConnection()).thenReturn(con);
            mocked.when(DBManager::getInstance)
                    .thenReturn(db);

            doCallRealMethod().when(db).findCourse(1);
            doCallRealMethod().when(db).getNumOfStudents(1);

            ChangeDuration changeDuration = new ChangeDuration();
            String page = changeDuration.execute(request, response);
            assertEquals("/views/detailOfCourse.jsp", page);
        } catch (SQLException | ParseException | DBException e) {
            e.printStackTrace();
        }
        mocked.close();
    }

    @Test
    public void CreateCourseTest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("topicName")).thenReturn("topicName");
        when(request.getParameter("courseName")).thenReturn("2 month");
        when(request.getParameter("start_day")).thenReturn("21.01.1975");
        doNothing().when(session).setAttribute(isA(String.class), isA(String.class));

        ResultSet rs = mock(ResultSet.class);
        Connection con = mock(Connection.class);
        PreparedStatement pstmt = mock(PreparedStatement.class);
        DBManager db = mock(DBManager.class);
        MockedStatic<DBManager> mocked =
                mockStatic(DBManager.class);

        try {
            when(db.checkCourse(isA(String.class)))
                    .thenReturn(false);
            when(db.createCourse(isA(String.class), isA(String.class), isA(String.class)))
                    .thenReturn(true);

            when(pstmt.executeQuery()).thenReturn(rs);
            when(con.prepareStatement(SQL_FIND_COURSES_BY_TOPIC)).thenReturn(pstmt);

            when(rs.next())
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(false);
            when(rs.getInt("id"))
                    .thenReturn(1)
                    .thenReturn(2);
            when(rs.getString("course_name"))
                    .thenReturn("Back-end development")
                    .thenReturn("Big Data");
            when(rs.getString("course_topic"))
                    .thenReturn("JAVA")
                    .thenReturn("Python");
            when(rs.getString("start_day"))
                    .thenReturn("2022-02-14")
                    .thenReturn("2022-05-15");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        when(db.getConnection()).thenReturn(con);

        mocked.when(DBManager::getInstance)
                .thenReturn(db);

        CreateCourse course = new CreateCourse();
        try {
            String page = course.execute(request, response);
            assertEquals("/views/AllCourses.jsp", page);
        } catch (DBException | ParseException e) {
            e.printStackTrace();
        }
        mocked.close();
    }

    @Test
    public void CreateCourseTest2() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("topicName")).thenReturn("topicName");
        when(request.getParameter("courseName")).thenReturn("2 month");
        when(request.getParameter("start_day")).thenReturn("21.01.1975");
        doNothing().when(session).setAttribute(isA(String.class), isA(String.class));

        ResultSet rs = mock(ResultSet.class);
        Connection con = mock(Connection.class);
        PreparedStatement pstmt = mock(PreparedStatement.class);
        DBManager db = mock(DBManager.class);
        MockedStatic<DBManager> mocked =
                mockStatic(DBManager.class);

        try {
            when(db.checkCourse(isA(String.class)))
                    .thenReturn(true);
            when(db.updateTopicOfCourse(isA(String.class), isA(String.class), isA(String.class)))
                    .thenReturn(true);

            when(pstmt.executeQuery()).thenReturn(rs);
            when(con.prepareStatement(SQL_FIND_COURSES_BY_TOPIC)).thenReturn(pstmt);

            when(rs.next())
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(false);
            when(rs.getInt("id"))
                    .thenReturn(1)
                    .thenReturn(2);
            when(rs.getString("course_name"))
                    .thenReturn("Back-end development")
                    .thenReturn("Big Data");
            when(rs.getString("course_topic"))
                    .thenReturn("JAVA")
                    .thenReturn("Python");
            when(rs.getString("start_day"))
                    .thenReturn("2022-02-14")
                    .thenReturn("2022-05-15");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        when(db.getConnection()).thenReturn(con);

        mocked.when(DBManager::getInstance)
                .thenReturn(db);

        CreateCourse course = new CreateCourse();
        try {
            String page = course.execute(request, response);
            assertEquals("/views/AllCourses.jsp", page);
        } catch (DBException | ParseException e) {
            e.printStackTrace();
        }
        mocked.close();
    }
}
