package commandTest;

import app.db.DBException;
import app.db.DBManager;
import app.model.command.registerCommand.GoToRegisterPage;
import app.model.command.registerCommand.RegisterCommand;
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

import static app.db.SQLQueries.SQL_FIND_USER_BY_PHONE;
import static org.mockito.Mockito.*;

public class RegisterCommandTest extends Assert {
    @Test
    public void registerCommandTest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

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

        when(db.chekLogin("login")).thenReturn(true);
        when(db.chekPhone("phone")).thenReturn(true);
        doNothing().when(session).setAttribute(isA(String.class), isA(Object.class));

        RegisterCommand registerCommand = new RegisterCommand();
        String page = null;
        try {
            page = registerCommand.execute(request, response);
            assertEquals(page, "/views/registration.jsp");
        } catch (DBException e) {
            e.printStackTrace();
        }
        mocked.close();
    }

    @Test
    public void GoToRegisterPageTest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        GoToRegisterPage registerPage = new GoToRegisterPage();
        try {
            String actual = registerPage.execute(request, response);
            assertEquals(actual, "/views/registration.jsp");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
