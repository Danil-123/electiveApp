package app.Controller;

import app.db.DBException;
import app.model.command.Command;
import app.model.command.CommandFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;

/**
 * @author Danil Shatov
 * <p>
 * Servlet Controller
 */

@WebServlet("/controller")
public class Controller extends HttpServlet {

    private final Logger log = LogManager.getLogger(this.getClass().toString());

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String address = processRequest(req, resp);
        req.getRequestDispatcher(address).forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String address = processRequest(req, resp);
        resp.sendRedirect(address);
    }

    /**
     * main method which define the address of the page.
     *
     * @param request,response.
     * @return address.
     * @throws IOException On input error.
     */
    private String processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        String address = "error";
        /**
         * define a command that came from a JSP
         */
        CommandFactory client = new CommandFactory();
        Command command = client.defineCommand(request);

        if (command != null) {
            try {
                address = command.execute(request, response);
            } catch (ParseException | DBException e) {
                log.error(e.getMessage());
                session.setAttribute("error", e.getMessage());
            }
        } else {
            /**
             * set page with error message
             */
            address = "/index.jsp";
            session.setAttribute("nullPage",
                    "Page not found. Business logic error.");
            response.sendRedirect(request.getContextPath() + address);
        }
        log.info("address = " + address);
        return address;
    }
}