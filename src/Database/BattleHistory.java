package Database;

import java.util.Calendar;
public class BattleHistory {
    private int ownCrown;
    private int notOwnCrown;
    private Calendar calendar;
    private Result result;
    private String opponentName;

    @Override
    public String toString() {
        return "BattleHistory{" +
                "ownCrown=" + ownCrown +
                ", notOwnCrown=" + notOwnCrown +
                ", date=" + calendar +
                ", result=" + result +
                '}';
    }

    enum Result {
        WIN,LOOSE
    }
}
