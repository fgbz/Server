package phalaenopsis.fgbz.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by 13260 on 2018/4/14.
 */
public class NFDFlightDataTaskListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        new TimerManager(sce);
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
