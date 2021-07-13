package Database;

import java.util.Date;

public class BattleHistory {
    private int wonCrown;
    private int lostCrown;
    private Date date;
    private Result result;
    private String opponentName;

    public BattleHistory(String opponentName, Date date,int wonCrown,int lostCrown, Result result) {
        this.wonCrown = wonCrown;
        this.lostCrown = lostCrown;
        this.date = date;
        this.result = result;
        this.opponentName = opponentName;
    }

    @Override
    public String toString() {
        return "BattleHistory{" +
                "ownCrown=" + wonCrown +
                ", notOwnCrown=" + lostCrown +
                ", date=" + date +
                ", result=" + result +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public Result getResult() {
        return result;
    }

    public int getWonCrown() {
        return wonCrown;
    }

    public int getLostCrown() {
        return lostCrown;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public enum Result {
        WIN,LOOSE
    }
}
