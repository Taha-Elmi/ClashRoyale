package Database;

import Main.Config;
import Models.Cards.Card;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLManager {

    /**
     * Connects to the game's database.
     */
    public static void connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306";
        String user = "root";
        String pass = "99clash31royale";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Config.statement = connection.createStatement();
            Config.statement.execute("use clash_royale");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * gets a card and return its id used in the database.
     * @param card the card we want its id
     * @return the id of the card
     */
    public static int cardToIndex(Card card) {
        String cardName = card.getClass().getSimpleName();
        int index = 0;
        try {
            String query = "select id from cards where name='" + cardName + "';";
            Config.statement.execute(query);
            ResultSet resultSet = Config.statement.getResultSet();
            resultSet.next();
            index = resultSet.getInt("id");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        assert index != 0;
        return index;
    }

    /**
     * gets a card id from database and return the appropriate card.
     * @param id the card id gotten from the database
     * @return the appropriate card
     */
    public static Card indexToCard(int id) {
        String cardName = "";
        String query = "select name from cards where id=" + id + ";";
        try {
            Config.statement.execute(query);
            ResultSet resultSet = Config.statement.getResultSet();
            resultSet.next();
            cardName = resultSet.getString("name");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        for (Card card : Config.cards) {
            if (card.getClass().getSimpleName().equals(cardName))
                return card;
        }
        return null;
    }

    public static void updateClientDeck() {
        for (int i = 1; i <= 8; i++) {
            int index = cardToIndex(Config.client.getDeckCards().get(i - 1));
            try {
                String query = "update clients set card" + i + " = " + index + " where name='" + Config.client.getName() + "';";
                Config.statement.execute(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
