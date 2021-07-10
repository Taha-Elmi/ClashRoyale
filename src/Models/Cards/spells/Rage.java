package Models.Cards.spells;

import Main.Config;

public class Rage extends Spell {
    private double duration;
    public Rage(int cost,int level,int radius) {
        super(cost,level,radius);
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getDuration() {
        return duration;
    }

    @Override
    protected void setLevel(int level) {
        if (level == 1) {
            setDuration(6);
        } else if (level == 2) {
            setDuration(6.5);
        } else if (level == 3) {
            setDuration(7);
        } else if (level == 4) {
            setDuration(7.5);
        } else if (level == 5) {
            setDuration(8);
        } else {
            Config.unknownInputException();
        }
    }

    @Override
    public void born() {

    }

    @Override
    public void die() {

    }

    @Override
    protected boolean isDead() {
        return false;
    }

    @Override
    public void run() {

    }
}
