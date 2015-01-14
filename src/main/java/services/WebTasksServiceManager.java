package services;

import database.DataService;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;

public final class WebTasksServiceManager {
    private static final Logger LOGGER = Logger.getLogger(WebTasksServiceManager.class);
    private static final String WEBTASKS_SERVICES_MANAGER = "WEBTASKS_SERVICES_MANAGER";
    private static final WebTasksServiceManager INSTANCE = new WebTasksServiceManager();

    private WebTasksServiceManager() {
        init();
    }

    public static WebTasksServiceManager getInstance(ServletContext context) {
        WebTasksServiceManager instance = (WebTasksServiceManager) context.getAttribute(WEBTASKS_SERVICES_MANAGER);
        if (instance == null) {
            context.setAttribute(WEBTASKS_SERVICES_MANAGER, INSTANCE);
            instance = INSTANCE;
        }
        return instance;
    }

    private DataService dataService;

    private void init() {
        dataService = new DataService();
    }

    public void startAllServices() {
        try {
            dataService.init();
        } catch (Exception e) {
            LOGGER.info("Context can't started. '" + e.getMessage());
            throw new RuntimeException("Context can't started. '" + e.getMessage(), e);
        }
        LOGGER.info("All services have been started");
    }

    public void closeAllServices() {
        dataService.close();
        LOGGER.info("All services have been closed");
    }
}
