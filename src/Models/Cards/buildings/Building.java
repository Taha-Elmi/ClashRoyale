package Models.Cards.buildings;

import Controllers.GameCon;
import Models.Cards.Card;
import Models.Cards.CardImage;
import Models.Cards.Target;
import Models.Cards.spells.Spell;
import Models.Cards.troops.Troop;
import Models.GameManager.Game;
import Models.Graphic.FXManager;
import Models.Interfaces.Damageable;
import Models.Interfaces.Hitter;
import Models.Towers.Tower;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

abstract public class Building extends Card implements Hitter, Damageable,Runnable {
    private int hp;
    private double hitSpeed;
    private Target target;
    private double range;
    private int lifetime;

    public double getHitSpeed() {
        return hitSpeed;
    }

    public Target getTarget() {
        return target;
    }

    public double getRange() {
        return range;
    }

    public int getLifetime() {
        return lifetime;
    }

    public Building(int cost, int level, double hitSpeed,
                    Target target, double range, int lifetime) {
        super(cost,level,1);
        this.hitSpeed = hitSpeed;
        this.target = target;
        this.range = range;
        this.lifetime = lifetime;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    @Override
    public void gotDamage(int damage) {
        hp -= damage;
        if (isDead())
            die();
    }

    @Override
    public boolean isDead() {
        return hp <= 0;
    }

    public abstract void readyForBorn(ImageView imageView, int playerNumber, Point2D src);

    @Override
    public void hit(Damageable damageable) {
        if (this instanceof Cannon) {
            Cannon cannon = (Cannon) this;
            damageable.gotDamage(cannon.getDamage());
        } else {}
    }

}
