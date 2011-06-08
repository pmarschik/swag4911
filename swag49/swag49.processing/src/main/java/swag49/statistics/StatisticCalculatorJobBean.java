package swag49.statistics;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class StatisticCalculatorJobBean extends QuartzJobBean {


    private static final String CALCULATOR_KEY = "calculator";

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        StatisticCalculator calculator = (StatisticCalculator) context.getJobDetail().getJobDataMap().get(CALCULATOR_KEY);
        try {
            calculator.calculate();
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }
}
