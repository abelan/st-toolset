package com.softteco.toolset.scheduler;

import com.google.inject.Singleton;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author serge
 */
@Singleton
public final class SchedulerManager {

    private static final int TWO_SECS = 2000;
    private static final Logger LOGGER = LogManager.getLogger(SchedulerManager.class.getName());
    private Scheduler scheduler;

    public SchedulerManager() {
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.clear();
            scheduler.start();
        } catch (SchedulerException e) {
            LOGGER.error("Problem with scheduler", e);
        }
    }

    public void addJob(final JobDetail jd) {
        final Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jd.getKey().getName()).
                startAt(new Date(System.currentTimeMillis() + TWO_SECS)).build();
        addJob(jd, trigger);
    }

    public void addJob(final JobDetail jd, final Trigger trigger) {
        try {
            scheduler.scheduleJob(jd, trigger);
        } catch (SchedulerException e) {
            LOGGER.error("Problem with scheduler", e);
        }
    }
}
