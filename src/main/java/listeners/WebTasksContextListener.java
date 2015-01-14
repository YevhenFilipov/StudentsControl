package listeners;

import constants.Constants;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import services.WebTasksServiceManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class WebTasksContextListener implements ServletContextListener {
    private static final Logger LOGGER = Logger.getLogger(WebTasksContextListener.class);

    protected String getContext(ServletContextEvent sce) {
        final String context = sce.getServletContext().getContextPath();
        return StringUtils.isBlank(context) ? "ROOT" : context;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        final String context = sce.getServletContext().getContextPath();
        sce.getServletContext().setAttribute(Constants.CONTEXT, context);

        final WebTasksServiceManager webtasksServiceManager = WebTasksServiceManager.getInstance(sce.getServletContext());
        webtasksServiceManager.startAllServices();

        LOGGER.info("Context '" + getContext(sce) + "' started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        final WebTasksServiceManager webtasksServiceManager = WebTasksServiceManager.getInstance(sce.getServletContext());
        webtasksServiceManager.closeAllServices();

        LOGGER.info("Context '" + getContext(sce) + "' stopped");
    }
}
