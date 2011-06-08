package swag49.statistics;

import swag49.model.Player;

public class MostResourcesCalculator extends StatisticCalculatorBase {

    public static final String NAME = "Most resources";

    @Override
    protected String getRankedPlayersQuery() {
        return "select player from Player player order by (player.resources.amount_crops + player.resources.amount_gold + player.resources.amount_stone + player.resources.amount_wood) desc, player.id asc";
    }

    @Override
    protected int getScore(Player player) {
        return player.getResources().getAmount_crops() + player.getResources().getAmount_gold() + player.getResources().getAmount_stone() + player.getResources().getAmount_wood();
    }

    @Override
    protected String getStatisticName() {
        return NAME;
    }
}
