package swag49.statistics;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class StatisticCalculatorJobBean extends QuartzJobBean {

    private static final String APPLICATION_CONTEXT_KEY = "applicationContext";

    private String calculatorId;

    public void setCalculatorId(String calculatorId) {
        this.calculatorId = calculatorId;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            ApplicationContext appContext = getApplicationContext(context);
            StatisticCalculator calculator = appContext.getBean(calculatorId, StatisticCalculator.class);

            calculator.calculate();
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }

    public ApplicationContext getApplicationContext(JobExecutionContext context) throws SchedulerException {
        SchedulerContext schedulerContext = context.getScheduler().getContext();
        ApplicationContext applicationContext = (ApplicationContext) schedulerContext.get(APPLICATION_CONTEXT_KEY);
        return applicationContext;
    }
}
