package Models.Cards.troops;

import Main.Config;

public enum Speed {
    SLOW,MEDIUM,FAST;
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
