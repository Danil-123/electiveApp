package commandTest;

import app.db.DBException;
import app.db.DBManager;
import app.entities.User;
import app.model.command.loginCommand.LoginCommand;
import app.model.command.loginCommand.LogoutCommand;
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

import static app.db.SQLQueries.SQL_FIND_USER_BY_LOGIN;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

public class loginCommandTest extends Assert {
    @Test
    public void LoginCommandTest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("password")).thenReturn("password");
        doNothing().when(session).setAttribute(isA(String.class), isA(String.class));

        ResultSet rs = mock(ResultSet.class);
        Connection con = mock(Connection.class);
        PreparedStatement pstmt = mock(PreparedStatement.class);
        DBManager db = mock(DBManager.class);
        MockedStatic<DBManager> mocked =
                mockStatic(DBManager.class);
        try {
            when(pstmt.executeQuery()).thenReturn(rs);
            when(con.prepareStatement(SQL_FIND_USER_BY_LOGIN)).thenReturn(pstmt);
            when(rs.next())
                    .thenReturn(true);
            when(rs.getString("password"))
                    .thenReturn("password");
            when(rs.getString("login"))
                    .thenReturn("login");
            when(rs.getString("name"))
                    .thenReturn("name");
            when(rs.getString("surname"))
                    .thenReturn("surname");
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        when(db.getConnection()).thenReturn(con);

        mocked.when(DBManager::getInstance)
                .thenReturn(db);
        try {
            doCallRealMethod().when(db).findUser("some login");
            User actual = db.findUser("some login");
            assertTrue(actual != null);
            assertTrue(actual.getPassword().equals("password"));

            LoginCommand loginCommand = new LoginCommand();
            String page = loginCommand.execute(request, response);
            assertEquals("/views/login.jsp", page);
        } catch (DBException e) {
            e.printStackTrace();
        }
        mocked.close();
    }

    @Test
    public void LogOutCommandTest(){
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        LogoutCommand logoutCommand = new LogoutCommand();
        String page = logoutCommand.execute(request,response);
        assertEquals("/index.jsp",page);
    }
}
