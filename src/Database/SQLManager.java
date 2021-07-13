package Database;

import Main.Config;
import Models.Cards.Card;
import Models.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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

    public ArrayList getHistory(Client client) {
        ArrayList<BattleHistory> battleHistories = new ArrayList<>();
        try {
            String query = "select * from history where name='" + client.getName() + "';";
            Config.statement.execute(query);
            ResultSet resultSet = Config.statement.getResultSet();

            while (resultSet.next()) {
                String opponentName = resultSet.getString("opponent");
                Date date = resultSet.getDate("time");
                int wonCrowns = resultSet.getInt("crowns");
                int lostCrowns = resultSet.getInt("opponent_crowns");
                BattleHistory.Result result = (resultSet.getBoolean("result") ? BattleHistory.Result.WIN : BattleHistory.Result.LOOSE);
                battleHistories.add(new BattleHistory(opponentName, date, wonCrowns, lostCrowns, result));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return battleHistories;
    }

    public void addHistory(String name, int wonCrowns, int lostCrowns, BattleHistory.Result result) {
        try {
            String query = "insert into history values (" + name + ")";
            Config.statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
