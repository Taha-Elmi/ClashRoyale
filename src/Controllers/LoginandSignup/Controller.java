package Controllers.LoginandSignup;

import Database.SQLManager;
import Main.Config;
import Models.Cards.Card;
import Models.Client;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Parent of login controller and signup controller
 */
public abstract class Controller {
    protected final String labelFontName = "System";
    protected final int labelFontSize = 11;

    @FXML
    protected Button loginButton;

    @FXML
    protected Button signupButton;

    @FXML
    protected TextField usernameTextField;

    @FXML
    protected PasswordField passwordField;

    @FXML
    protected Label warningLabel;

    /**
     * what should be done if the other button was pressed.
     * @param actionEvent the event
     */
    @FXML
    protected void buttonPressed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == loginButton) {
            loginButtonPressed();
        } else if (actionEvent.getSource() == signupButton) {
            signupButtonPressed();
        }
    }

    /**
     * Initialize
     */
    public void initialize() {
        usernameTextField.styleProperty().bind(
                Bindings
                        .when(usernameTextField.focusedProperty())
                        .then("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);")
                        .otherwise("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);"));
        passwordField.styleProperty().bind(
                Bindings
                        .when(passwordField.focusedProperty())
                        .then("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);")
                        .otherwise("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);"));
        warningLabel.setFont(new Font("System",labelFontSize));
    }

    /**
     * what should be done on login button press
     */
    protected abstract void loginButtonPressed();

    /**
     * what should be done on signup button press
     */
    protected abstract void signupButtonPressed();

    /**
     * assigns associated client from database to the user client.
     * @param username username of the client
     */
    protected void assignClient(String username) {
        String query = "select * from clients where name='" + username + "';";
        try {
            Config.statement.execute(query);
            ResultSet resultSet = Config.statement.getResultSet();
            resultSet.next();
            String name = resultSet.getString("name");
            int level = resultSet.getInt("level");
            int xp = resultSet.getInt("xp");
            Config.client = new Client(name, xp, level);

            int[] indexes = new int[8];
            for (int i = 1; i <= 8; i++)
                indexes[i-1] = resultSet.getInt("card" + i);

            ArrayList<Card> cards = new ArrayList<>();
            for (int i = 0; i < 8; i++)
                cards.add(SQLManager.indexToCard(indexes[i]));
            Config.client.setDeckCards(cards);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
