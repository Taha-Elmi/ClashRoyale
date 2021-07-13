package Main;

import Models.Cards.Card;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLManager {

    /**
     * Connects to the game's database.
     */
    protected static void connectToDatabase() {
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
        String cardName = card.getClass().getName();
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

}
