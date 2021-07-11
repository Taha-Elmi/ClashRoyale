package Database;

import java.util.Date;

public class BattleHistory {
    private int ownCrown;
    private int notOwnCrown;
    private Date date;
    private Result result;
    enum Result {
        WIN,LOOSE
    }
}
