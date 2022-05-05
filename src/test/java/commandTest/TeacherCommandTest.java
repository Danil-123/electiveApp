package commandTest;

import app.db.DBException;
import app.db.DBManager;
import app.entities.Course;
import app.entities.User;
import app.model.command.TeacherCommand.GoToJournal;
import app.model.command.TeacherCommand.ListOfcourseByTeacher;
import app.model.command.TeacherCommand.SetMarkCommand;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class TeacherCommandTest extends Assert {
    @Test
    public void ListOfcourseByTeacherTest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("id_teacher")).thenReturn("id_teacher");
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
                    .thenReturn(false)
                    .thenReturn(true);
            when(rs.getInt("id_course"))
                    .thenReturn(1);
            when(rs.getInt("id"))
                    .thenReturn(1);
            when(rs.getString("course_name"))
                    .thenReturn("Back-end development");
            when(rs.getString("course_topic"))
                    .thenReturn("JAVA");
            when(rs.getString("start_day"))
                    .thenReturn("2022-02-14");

            when(db.getConnection()).thenReturn(con);

            mocked.when(DBManager::getInstance)
                    .thenReturn(db);

            ListOfcourseByTeacher listOfcourseByTeacher = new ListOfcourseByTeacher();
            String page = listOfcourseByTeacher.execute(request, response);
            assertEquals(page, "/views/AllTeachersCourses.jsp");

            doCallRealMethod().when(db).findAllCoursesByTeacher("1");
            List<Course> actual = db.findAllCoursesByTeacher("1");
            List<Course> expected = Arrays.asList(new Course(1, "Back-end development", "JAVA", "2022-02-14"));
            assertEquals(actual.toString(), expected.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mocked.close();
    }

    @Test
    public void goToJournalTest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("course_id")).thenReturn("5");
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
                    .thenReturn(false)
                    .thenReturn(true)
                    .thenReturn(true);
            when(rs.getString("student_id"))
                    .thenReturn("12");
            when(rs.getString("mark"))
                    .thenReturn("5");
            when(request.getParameter("login"))
                    .thenReturn("login");
            when(request.getParameter("psw"))
                    .thenReturn("password");
            when(request.getParameter("email"))
                    .thenReturn("email");
            when(request.getParameter("name"))
                    .thenReturn("name");
            when(request.getParameter("surname"))
                    .thenReturn("surname");
            when(request.getParameter("phone"))
                    .thenReturn("phone");
            when(request.getParameter("role"))
                    .thenReturn("role");

            when(db.getConnection()).thenReturn(con);

            mocked.when(DBManager::getInstance)
                    .thenReturn(db);

            GoToJournal goToJournal = new GoToJournal();
            String actual = goToJournal.execute(request, response);
            String expected = "/views/journal.jsp";
            assertEquals(actual, expected);


            doCallRealMethod().when(db).findAllStudents(isA(Integer.class));
            List<User> userList = db.findAllStudents(isA(Integer.class));
            assertTrue(userList.size() == 1);
        } catch (SQLException | DBException | ParseException e) {
            e.printStackTrace();
        }
        mocked.close();
    }

    @Test
    public void setMarkCommandTest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("id_student")).thenReturn("1");
        when(request.getParameter("mark")).thenReturn("5");
        when(request.getParameter("id_course")).thenReturn("5");
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
                    .thenReturn(false)
                    .thenReturn(true)
                    .thenReturn(true);
            when(rs.getString("student_id"))
                    .thenReturn("12");
            when(rs.getString("mark"))
                    .thenReturn("5");
            when(request.getParameter("login"))
                    .thenReturn("login");
            when(request.getParameter("psw"))
                    .thenReturn("password");
            when(request.getParameter("email"))
                    .thenReturn("email");
            when(request.getParameter("name"))
                    .thenReturn("name");
            when(request.getParameter("surname"))
                    .thenReturn("surname");
            when(request.getParameter("phone"))
                    .thenReturn("phone");
            when(request.getParameter("role"))
                    .thenReturn("role");
            when(db.getConnection()).thenReturn(con);

            mocked.when(DBManager::getInstance)
                    .thenReturn(db);
            when(db.setMark(isA(String.class), isA(String.class), isA(String.class))).thenReturn(true);

            String expected = "/views/journal.jsp";
            SetMarkCommand setMarkCommand = new SetMarkCommand();
            String actual = setMarkCommand.execute(request, response);
            assertEquals(actual, expected);

        } catch (SQLException | DBException | ParseException e) {
            e.printStackTrace();
        }
        mocked.close();
    }
}
