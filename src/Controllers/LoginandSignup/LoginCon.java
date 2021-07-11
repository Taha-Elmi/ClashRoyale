package Controllers.LoginandSignup;

import Main.Config;
import Models.Client;
import Models.Graphic.FXManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginCon extends Controller {

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label warningLabel;

    @Override
    protected void loginButtonPressed() {
        try {
            loginProcess();
        } catch (IllegalArgumentException e) {
            return;
        }
        FXManager.goTo("MainMenu.fxml",(Stage) loginButton.getScene().getWindow());
    }

    private void loginProcess() {
        warningLabel.setFont(new Font(labelFontName,labelFontSize));
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        String query = "select password from clients where name='" + username + "';";
        try {
            Config.statement.execute(query);
            ResultSet resultSet = Config.statement.getResultSet();
            boolean result = resultSet.next();
            if (!result) {
                warningLabel.setText("There is no account with this username.");
                throw new IllegalArgumentException();
            }

            if (!resultSet.getString("password").equals(password)) {
                warningLabel.setText("Password is incorrect.");
                throw new IllegalArgumentException();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("login");
        assignClient(username);
    }
    @Override
    protected void signupButtonPressed() {
        FXManager.goTo("signup.fxml",(Stage) signupButton.getScene().getWindow());
    }
}
