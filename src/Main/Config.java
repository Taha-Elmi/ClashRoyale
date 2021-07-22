package Main;
import Database.FileUtils;
import Models.Cards.Card;
import Models.Cards.CardImage;
import Models.Cards.buildings.Cannon;
import Models.Cards.buildings.InfernoTower;
import Models.Cards.spells.Arrows;
import Models.Cards.spells.FireBall;
import Models.Cards.spells.Rage;
import Models.Cards.troops.*;
import Models.Client;
import Models.Graphic.FXManager;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Config {
    //fields
    public static Stage primaryStage;
    public static Client client;
    public static Statement statement;
    public static ArrayList<Card> cards = new ArrayList<>();
    public static ArrayList<CardImage> cardImages = new ArrayList<>();
    public static MediaPlayer mediaPlayer;

    //methods
    public static void unknownInputException() {
        try {
            throw new Exception("Unknown Input!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playMusic(String path) {
        Media media = FileUtils.loadMedia(path);
        Config.mediaPlayer = new MediaPlayer(media);
        Config.mediaPlayer.play();
        Config.mediaPlayer.setOnEndOfMedia(() ->
                Config.mediaPlayer.seek(Duration.ZERO));
    }

    public static List<Card> getPlayer2Cards() {
        Random r = new Random();
        int randomNumberOrigin = 0;
        int randomNumberBound = 11;
        int size = 8;
        int[] unique = r.ints(randomNumberOrigin, randomNumberBound)
                .distinct()
                .limit(size)
                .toArray();
        List<Card> cloneOfCards = new ArrayList<>();
        for (Card card: cards) {
            try {
                cloneOfCards.add((Card) card.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        List<Card> player2Cards = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            player2Cards.add(cloneOfCards.get(unique[i]));
        }
        return player2Cards;
    }

    static {
        cards.add(new Archer(1));
        cards.add(new BabyDragon(1));
        cards.add(new Barbarian(1));
        cards.add(new Giant(1));
        cards.add(new MiniPekka(1));
        cards.add(new Valkyrie(1));
        cards.add(new Wizard(1));
        cards.add(new Arrows(1));
        cards.add(new FireBall(1));
        cards.add(new Rage(1));
        cards.add(new Cannon(1));
        cards.add(new InfernoTower(1));

        cardImages.add(new CardImage(cards.get(0), FXManager.getImage("/Cards/Archer.png")));
        cardImages.add(new CardImage(cards.get(1), FXManager.getImage("/Cards/BabyDragon.png")));
        cardImages.add(new CardImage(cards.get(2), FXManager.getImage("/Cards/Barbarian.png")));
        cardImages.add(new CardImage(cards.get(3), FXManager.getImage("/Cards/Giant.png")));
        cardImages.add(new CardImage(cards.get(4), FXManager.getImage("/Cards/MiniPekka.png")));
        cardImages.add(new CardImage(cards.get(5), FXManager.getImage("/Cards/Valkyrie.png")));
        cardImages.add(new CardImage(cards.get(6), FXManager.getImage("/Cards/Wizard.png")));
        cardImages.add(new CardImage(cards.get(7), FXManager.getImage("/Cards/Arrows.png")));
        cardImages.add(new CardImage(cards.get(8), FXManager.getImage("/Cards/FireBall.png")));
        cardImages.add(new CardImage(cards.get(9), FXManager.getImage("/Cards/Rage.png")));
        cardImages.add(new CardImage(cards.get(10), FXManager.getImage("/Cards/Cannon.png")));
        cardImages.add(new CardImage(cards.get(11), FXManager.getImage("/Cards/InfernoTower.png")));
    }


}
