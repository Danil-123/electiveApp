package commandTest;

import app.db.DBException;
import app.db.DBManager;
import app.model.command.sortCommand.SortByDuration;
import app.model.command.sortCommand.SortByName;
import app.model.command.sortCommand.SortByNumOfStudent;
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

import static app.db.SQLQueries.SQL_FIND_ALL_USERS_IN_COURSE;
import static app.db.SQLQueries.SQL_FIND_COURSES_BY_TOPIC;
import static org.mockito.Mockito.*;

public class SortCommandTest extends Assert {
    @Test
    public void SortingTest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        ResultSet rs = mock(ResultSet.class);
        Connection con = mock(Connection.class);
        PreparedStatement pstmt = mock(PreparedStatement.class);
        DBManager db = mock(DBManager.class);
        MockedStatic<DBManager> mocked =
                mockStatic(DBManager.class);
        when(session.getAttribute(isA(String.class))).thenReturn("name");

        try {
            when(pstmt.executeQuery()).thenReturn(rs);
            when(con.prepareStatement(SQL_FIND_COURSES_BY_TOPIC)).thenReturn(pstmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            when(rs.next())
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(false)
                    .thenReturn(true)
                    .thenReturn(true);

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

            when(rs.getString("duration")).thenReturn("2022-02-14");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        when(db.getConnection()).thenReturn(con);

        mocked.when(DBManager::getInstance)
                .thenReturn(db);

        SortByDuration sort = new SortByDuration();
        SortByName sortByName = new SortByName();
        try {
            String expected = "/views/AllCourses.jsp";
            String page = sort.execute(request, response);
            String page2 = sortByName.execute(request, response);
            assertEquals(page, expected);
            assertEquals(page2, expected);
        } catch (DBException e) {
            e.printStackTrace();
        }

        mocked.close();
    }

    @Test
    public void sortByNumOfStudentTest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        ResultSet rs = mock(ResultSet.class);
        Connection con = mock(Connection.class);
        PreparedStatement pstmt = mock(PreparedStatement.class);
        DBManager db = mock(DBManager.class);
        MockedStatic<DBManager> mocked =
                mockStatic(DBManager.class);
        when(session.getAttribute(isA(String.class))).thenReturn("name");

        try {
            when(pstmt.executeQuery()).thenReturn(rs);
            when(con.prepareStatement(SQL_FIND_ALL_USERS_IN_COURSE)).thenReturn(pstmt);
            when(rs.next())
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(false);

            when(db.getConnection()).thenReturn(con);

            mocked.when(DBManager::getInstance)
                    .thenReturn(db);

            SortByNumOfStudent sortByNumOfStudent = new SortByNumOfStudent();
            String actual = sortByNumOfStudent.execute(request, response);
            String expected = "/views/AllCourses.jsp";
            assertEquals(actual, expected);
        } catch (SQLException | DBException e) {
            e.printStackTrace();
        }
        mocked.close();
    }
}
















