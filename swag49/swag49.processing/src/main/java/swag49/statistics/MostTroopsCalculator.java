package swag49.statistics;

import org.springframework.stereotype.Component;
import swag49.model.Player;

@Component("mostTroopsCalculator")
public class MostTroopsCalculator extends StatisticCalculatorBase {

    public static final String NAME = "Most troops";

    @Override
    protected String getRankedPlayersQuery() {
        return "select player from Player player order by player.troops.size desc, player.id asc";
    }

    @Override
    protected int getScore(Player player) {
        return player.getTroops().size();
    }

    @Override
    protected String getStatisticName() {
        return NAME;
    }
}
