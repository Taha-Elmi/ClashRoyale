package Database;

import java.util.Date;

public class BattleHistory {
    private int wonCrowns;
    private int lostCrowns;
    private Date date;
    private Result result;
    private String opponentName;

    public BattleHistory(String opponentName, Date date, int wonCrowns, int lostCrowns, Result result) {
        this.opponentName = opponentName;
        this.date = date;
        this.wonCrowns = wonCrowns;
        this.lostCrowns = lostCrowns;
        this.result = result;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public int getWonCrowns() {
        return wonCrowns;
    }

    public int getLostCrowns() {
        return lostCrowns;
    }

    public Result getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "BattleHistory{" +
                "wonCrowns=" + wonCrowns +
                ", lostCrowns=" + lostCrowns +
                ", date=" + date +
                ", result=" + result +
                '}';
    }

    public enum Result {
        WIN,LOOSE
    }
}
