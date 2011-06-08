package swag49.statistics;

import org.quartz.Job;

import java.io.Serializable;

public interface StatisticCalculator extends Serializable {
    public void calculate();
}
