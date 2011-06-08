package swag49.statistics;

import org.springframework.stereotype.Component;
import swag49.model.Player;

@Component("mostBasesCalculator")
public class MostBasesCalculator extends StatisticCalculatorBase {

    public static final String NAME = "Most bases";

    @Override
    protected String getRankedPlayersQuery() {
        return "select player from Player player order by player.owns.size desc, player.id asc";
    }

    @Override
    protected int getScore(Player player) {
        return player.getOwns().size();
    }

    @Override
    protected String getStatisticName() {
        return NAME;
    }
}
