package Models.Cards.troops;

import Controllers.GameCon;
import Main.Config;
import Models.Cards.Card;
import Models.Cards.CardImage;
import Models.Cards.Target;
import Models.GameManager.Game;
import Models.Towers.Tower;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Wizard extends Troop {

    private final static Speed speed = Speed.MEDIUM;

    public Wizard(int level) {
        super(5, level,1);
        setSpeed(Speed.MEDIUM);
        setHitSpeed(1.7);
        setTargetCategory(Target.AIRandGROUND);
        setRange(5 * 25);
        setAreaSplash(true);
    }

    @Override
    public void setLevel(int level) {
        if (level == 1) {
            setHp(340);
            setDamage(130);
        } else if (level == 2) {
            setHp(374);
            setDamage(143);
        } else if (level == 3) {
            setHp(411);
            setDamage(157);
        } else if (level == 4) {
            setHp(452);
            setDamage(172);
        } else if(level == 5) {
            setHp(496);
            setDamage(189);
        } else {
            Config.unknownInputException();
        }
    }

    @Override
    public void damageEmote() {
        Circle circle = new Circle();
        circle.setRadius(8);
        circle.setFill(Color.YELLOW);

        CardImage cardImage = Game.getInstance().cardToCardImage(this);
        ImageView imageView = GameCon.getInstance().find(cardImage.getImage());
        Point2D src = new Point2D(imageView.getX(), imageView.getY());

        Point2D dst = null;
        if (getTarget() instanceof Card) {
            cardImage = Game.getInstance().cardToCardImage((Card) getTarget());
            imageView = GameCon.getInstance().find(cardImage.getImage());
            dst = new Point2D(imageView.getX(), imageView.getY());
        } else if (getTarget() instanceof Tower){
            int playerNumber = (cardImage.isGoingForward() ? 1 : 2);
            imageView = GameCon.getInstance().getNearerTowerImageView(src, playerNumber);
            dst = new Point2D(imageView.getX(), imageView.getY());
        } else {
            Config.unknownInputException();
        }

        circle.setCenterX(src.getX());
        circle.setCenterY(src.getY());
        double duration = src.distance(dst) / 50;
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.seconds(duration),
                new KeyValue(circle.centerXProperty(), dst.getX()),
                new KeyValue(circle.centerYProperty(), dst.getY())
        ));
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameCon.getInstance().getBoardPane().getChildren().remove(circle);
            }
        });
        GameCon.getInstance().getBoardPane().getChildren().add(circle);
        timeline.play();
    }

    @Override
    public void setSpeedToDefault() {
        setSpeed(speed);
    }
}
