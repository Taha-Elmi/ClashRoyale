package Models.Cards.spells;

import Main.Config;

public class Arrows extends Spell {
    private int areaDamage;
    public Arrows(int cost,int level,int radius) {
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
            setAreaDamage(144);
        } else if (level == 2) {
            setAreaDamage(156);
        } else if (level == 3) {
            setAreaDamage(174);
        } else if (level == 4) {
            setAreaDamage(189);
        } else if (level == 5) {
            setAreaDamage(210);
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
