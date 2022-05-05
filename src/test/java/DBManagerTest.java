import app.db.DBException;
import app.db.DBManager;
import app.entities.Course;
import app.entities.Topic;
import app.entities.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

import static app.db.SQLQueries.*;
import static org.mockito.Mockito.*;

public class DBManagerTest extends Assert {
    @Test
    public void findAllTopicTest() {
        ResultSet rs = mock(ResultSet.class);
        try {
            when(rs.next())
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(false);

            when(rs.getInt("id"))
                    .thenReturn(1)
                    .thenReturn(2);

            when((rs.getString("name")))
                    .thenReturn("JAVA")
                    .thenReturn("Phyton");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Statement stmt = mock((Statement.class));
        Connection con = mock(Connection.class);

        try {
            when(stmt.executeQuery(SQL_FIND_ALL_TOPIC)).thenReturn(rs);
            when(con.createStatement()).thenReturn(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBManager db = mock(DBManager.class);
        when(db.getConnection()).thenReturn(con);

        MockedStatic<DBManager> mocked =
                mockStatic(DBManager.class);

        mocked.when(DBManager::getInstance)
                .thenReturn(db);

        List<Topic> allTopic = null;
        try {
            doCallRealMethod().when(db).findAllTopic();
            allTopic = db.findAllTopic();
        } catch (DBException e) {
            e.printStackTrace();
        }

        String[] expected = {"JAVA", "Phyton"};
        int i = 0;
        for (Topic topic : allTopic) {
            assertEquals(expected[i], topic.getName());
            i++;
        }
        assertTrue(allTopic.size() == 2);
        mocked.close();
    }

    @Test
    public void findAllTeacherTest() {
        ResultSet rs = mock(ResultSet.class);
        Connection con = mock(Connection.class);
        PreparedStatement pstmt = mock(PreparedStatement.class);
        DBManager db = mock(DBManager.class);
        MockedStatic<DBManager> mocked =
                mockStatic(DBManager.class);

        try {
            when(pstmt.executeQuery()).thenReturn(rs);
            when(con.prepareStatement(SQL_FIND_ALL_TEACHER)).thenReturn(pstmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            when(rs.next())
                    .thenReturn(true)
                    .thenReturn(false);

            when(rs.getString("password"))
                    .thenReturn("1946");

            when(rs.getString("login"))
                    .thenReturn("Nikolay");

            when(rs.getString("name"))
                    .thenReturn("Nikolay");

            when(rs.getString("surname"))
                    .thenReturn("Sergeevich");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        when(db.getConnection()).thenReturn(con);

        mocked.when(DBManager::getInstance)
                .thenReturn(db);

        List<User> actual = null;
        try {
            doCallRealMethod().when(db).findAllTeacher();
            actual = db.findAllTeacher();
        } catch (DBException e) {
            e.printStackTrace();
        }

        User expected = User.createUser("Nikolay", "1946", null, "Nikolay", "Sergeevich", null, null);

        assertEquals(actual.get(0).toString(), expected.toString());
        assertTrue(actual.size() == 1);
        mocked.close();
    }

    @Test
    public void chekPhoneTest() {
        ResultSet rs = mock(ResultSet.class);
        Connection con = mock(Connection.class);
        PreparedStatement pstmt = mock(PreparedStatement.class);
        DBManager db = mock(DBManager.class);
        MockedStatic<DBManager> mocked =
                mockStatic(DBManager.class);

        try {
            when(pstmt.executeQuery()).thenReturn(rs);
            when(con.prepareStatement(SQL_FIND_USER_BY_PHONE)).thenReturn(pstmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            when(rs.next())
                    .thenReturn(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        when(db.getConnection()).thenReturn(con);

        mocked.when(DBManager::getInstance)
                .thenReturn(db);

        doCallRealMethod().when(db).chekPhone("some phone");
        boolean actual = db.chekPhone("some phone");

        assertTrue(actual);
        mocked.close();
    }

    @Test
    public void chekLoginTest() {
        ResultSet rs = mock(ResultSet.class);
        Connection con = mock(Connection.class);
        PreparedStatement pstmt = mock(PreparedStatement.class);
        DBManager db = mock(DBManager.class);
        MockedStatic<DBManager> mocked =
                mockStatic(DBManager.class);

        try {
            when(pstmt.executeQuery()).thenReturn(rs);
            when(con.prepareStatement(SQL_FIND_USER_BY_LOGIN)).thenReturn(pstmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            when(rs.next())
                    .thenReturn(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        when(db.getConnection()).thenReturn(con);

        mocked.when(DBManager::getInstance)
                .thenReturn(db);

        doCallRealMethod().when(db).chekLogin("some login");
        boolean actual = db.chekLogin("some login");

        assertTrue(actual);
        mocked.close();
    }

    @Test
    public void findCoursesByTopicTest() {
        ResultSet rs = mock(ResultSet.class);
        Connection con = mock(Connection.class);
        PreparedStatement pstmt = mock(PreparedStatement.class);
        DBManager db = mock(DBManager.class);
        MockedStatic<DBManager> mocked =
                mockStatic(DBManager.class);

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
        List<Course> actual = null;
        try {
            doCallRealMethod().when(db).findCoursesByTopic("some topic");
            actual = db.findCoursesByTopic("some topic");
        } catch (DBException e) {
            e.printStackTrace();
        }
        List<Course> expected = Arrays.asList(
                new Course(1, "Back-end development", "JAVA", "2022-02-14"),
                new Course(2, "Big Data", "Python", "2022-05-15"));

        assertTrue(actual.size() == 2);
        assertEquals((actual.get(0)).toString(), (expected.get(0).toString()));
        assertEquals((actual.get(1)).toString(), (expected.get(1).toString()));

        mocked.close();
    }

    @Test
    public void getNumOfStudentsTest() {
        ResultSet rs = mock(ResultSet.class);
        Connection con = mock(Connection.class);
        PreparedStatement pstmt = mock(PreparedStatement.class);
        DBManager db = mock(DBManager.class);
        MockedStatic<DBManager> mocked =
                mockStatic(DBManager.class);

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
            doCallRealMethod().when(db).getNumOfStudents(1);
            int count = db.getNumOfStudents(1);
            assertTrue(count == 2);
        } catch (SQLException | DBException e) {
            e.printStackTrace();
        }
        mocked.close();
    }

    @Test
    public void checkDescriptionTest() {
        ResultSet rs = mock(ResultSet.class);
        Connection con = mock(Connection.class);
        PreparedStatement pstmt = mock(PreparedStatement.class);
        DBManager db = mock(DBManager.class);
        MockedStatic<DBManager> mocked =
                mockStatic(DBManager.class);

        try {
            when(pstmt.executeQuery()).thenReturn(rs);
            when(con.prepareStatement(SQL_FIND_COURSE_DESCRIPTION)).thenReturn(pstmt);
            when(rs.next())
                    .thenReturn(true);

            when(db.getConnection()).thenReturn(con);
            mocked.when(DBManager::getInstance)
                    .thenReturn(db);
            doCallRealMethod().when(db).checkDescription(1);
            boolean actual = db.checkDescription(1);
            assertTrue(actual);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mocked.close();
    }
}