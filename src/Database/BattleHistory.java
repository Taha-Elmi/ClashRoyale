package Database;

import java.util.Date;

public class BattleHistory {
    private int ownCrown;
    private int notOwnCrown;
    private Date date;
    private Result result;

    @Override
    public String toString() {
        return "BattleHistory{" +
                "ownCrown=" + ownCrown +
                ", notOwnCrown=" + notOwnCrown +
                ", date=" + date +
                ", result=" + result +
                '}';
    }

    enum Result {
        WIN,LOOSE
    }
}
