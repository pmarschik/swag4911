package swag49.statistics;

import swag49.model.Player;

public class MostBasesEvaluatorJob extends StatisticJobBase {

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
