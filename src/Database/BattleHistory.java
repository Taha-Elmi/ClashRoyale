package Database;

import java.util.Date;

/**
 * Battle History Objects that will be saved in database
 */
public class BattleHistory {
    private int wonCrown;
    private int lostCrown;
    private Date date;
    private Result result;
    private String opponentName;

    /**
     * constructor
     * @param opponentName opponent name
     * @param date date
     * @param wonCrown number of won crowns
     * @param lostCrown number of lost crowns
     * @param result result of the match
     */
    public BattleHistory(String opponentName, Date date,int wonCrown,int lostCrown, Result result) {
        this.wonCrown = wonCrown;
        this.lostCrown = lostCrown;
        this.date = date;
        this.result = result;
        this.opponentName = opponentName;
    }

    /**
     * converts a battle history to string
     * @return the string
     */
    @Override
    public String toString() {
        return "BattleHistory{" +
                "ownCrown=" + wonCrown +
                ", notOwnCrown=" + lostCrown +
                ", date=" + date +
                ", result=" + result +
                '}';
    }

    /**
     * getter of date
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * getter of the result
     * @return the result
     */
    public Result getResult() {
        return result;
    }

    /**
     * getter of the won crowns
     * @return won crowns
     */
    public int getWonCrown() {
        return wonCrown;
    }

    /**
     * getter of the lost crowns
     * @return lost crowns
     */
    public int getLostCrown() {
        return lostCrown;
    }

    /**
     * getter of the opponent's name
     * @return opponent's name
     */
    public String getOpponentName() {
        return opponentName;
    }

    /**
     * result enum
     */
    public enum Result {
        WIN,LOOSE
    }
}
