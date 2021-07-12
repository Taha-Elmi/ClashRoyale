package Controllers.LoginandSignup;

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
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    @FXML
    protected void buttonPressed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == loginButton) {
            loginButtonPressed();
        } else if (actionEvent.getSource() == signupButton) {
            signupButtonPressed();
        }
    }
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
    protected abstract void loginButtonPressed();
    protected abstract void signupButtonPressed();

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
            for (int i = 0; i < 8; i++) {
                cards.add(Config.indexToCard(indexes[i]));
                System.out.println(cards.get(i).getClass().getSimpleName());
            }

            Config.client.setDeckCards(cards);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
