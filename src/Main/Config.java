package Main;
import Models.Cards.Card;
import Models.Cards.buildings.Cannon;
import Models.Cards.buildings.InfernoTower;
import Models.Cards.spells.Arrows;
import Models.Cards.spells.FireBall;
import Models.Cards.spells.Rage;
import Models.Cards.troops.*;
import Models.Client;
import javafx.stage.Stage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Config {
    //fields
    public static Stage primaryStage;
    public static Client client;
    public static Statement statement;
    public static ArrayList<Card> cards = new ArrayList<>();

    //methods
    public static void unknownInputException() {
        try {
            throw new Exception("Unknown Input!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void fillCards() {
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
    }

    public static Card indexToCard(int id) {
        String cardName = "";
        String query = "select name from cards where id=" + id + ";";
        try {
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            cardName = resultSet.getString("name");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        for (Card card : cards) {
            if (card.getClass().getSimpleName().equals(cardName))
                return card;
        }
        return null;
    }

    public static int cardToIndex(Card card) {
        String cardName = card.getClass().getName();
        int index = 0;
        try {
            String query = "select id from cards where name='" + cardName + "';";
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            index = resultSet.getInt("id");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        assert index != 0;
        return index;
    }
}
