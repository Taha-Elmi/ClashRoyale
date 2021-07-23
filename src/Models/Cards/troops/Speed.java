package Models.Cards.troops;

import Main.Config;

/**
 * used to categorized speed
 */
public enum Speed {
    SLOW,MEDIUM,FAST,RAGE_SLOW,RAGE_MEDIUM,RAGE_FAST;

    /**
     * converts enum values to double
     * @param speed speed
     * @return double value
     */
    public static double toDouble(Speed speed) {
        if (speed == Speed.SLOW) {
            return 7;
        } else if (speed == Speed.MEDIUM) {
            return 17;
        } else if (speed == Speed.FAST) {
            return 25;
        } else {
            Config.unknownInputException();
        }
        return -1;
    }
}
