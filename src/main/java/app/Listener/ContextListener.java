package app.Listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        String path = ctx.getRealPath("/WEB-INF/log4j2.log");
        System.setProperty("logFile", path);

        final Logger log = LogManager.getLogger(ContextListener.class);
        log.info("app stating work...");
        log.debug("path = " + path);

        // obtain file name with locales descriptions
        String localesFileName = ctx.getInitParameter("locales");
        log.info("localesFileName = " + localesFileName);
        // obtain real path on server
        String localesFileRealPath = ctx.getRealPath(localesFileName);
        log.info("localesFileRealPath = " + localesFileRealPath);
        // load descriptions
        Properties locales = new Properties();
        try {
            locales.load(new FileInputStream(localesFileRealPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // save descriptions to servlet context
        ctx.setAttribute("locales", locales);
    }
}