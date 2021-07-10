package Models.Cards.spells;

import Main.Config;

public class FireBall extends Spell {
    private int areaDamage;
    public FireBall(int cost,int level,int radius) {
        super(cost,level,radius);
    }

    public void setAreaDamage(int areaDamage) {
        this.areaDamage = areaDamage;
    }

    public int getAreaDamage() {
        return areaDamage;
    }

    @Override
    protected void setLevel(int level) {
        if (level == 1) {
            setAreaDamage(325);
        } else if (level == 2) {
            setAreaDamage(357);
        } else if (level == 3) {
            setAreaDamage(393);
        } else if (level == 4) {
            setAreaDamage(432);
        } else if (level == 5) {
            setAreaDamage(474);
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
