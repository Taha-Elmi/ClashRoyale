package Main;
import Models.Cards.Card;
import Models.Cards.troops.Archer;
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

    public Card indexToCard(int id) {
        String cardName = "";
        String query = "select from cards where id=" + id;
        try {
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            cardName = resultSet.getString("name");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return null;
    }
}
