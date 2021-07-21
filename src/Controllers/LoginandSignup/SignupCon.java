package Controllers.LoginandSignup;

import Main.Config;
import Models.Graphic.FXManager;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.sql.SQLException;

public class SignupCon extends Controller {

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label warningLabel;

    @Override
    public void initialize() {
        super.initialize();
        confirmPasswordField.styleProperty().bind(
                Bindings
                        .when(confirmPasswordField.focusedProperty())
                        .then("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);")
                        .otherwise("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);"));
    }
    @Override
    protected void loginButtonPressed() {
        FXManager.goTo("login.fxml",(Stage) loginButton.getScene().getWindow());
    }

    @Override
    protected void signupButtonPressed() {
        try {
            signupProcess();
        } catch (IllegalArgumentException e) {
            return;
        }
        Config.playMusic("assets/musics/MainTheme.mp3");
        FXManager.goTo("MainMenu.fxml",(Stage) signupButton.getScene().getWindow());
    }
    private void signupProcess() throws IllegalArgumentException {
        warningLabel.setFont(new Font(labelFontName,labelFontSize));
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (!password.equals(confirmPassword)) {
            warningLabel.setText("Password fields aren't equal.");
            throw new IllegalArgumentException();
        }

        try {
            String query = "insert into clients(name, password) values('" + username + "', '" + password + "');";
            Config.statement.execute(query);
        } catch (SQLException throwable) {
            warningLabel.setText("This username is already taken.");
            throw new IllegalArgumentException();
        }

        System.out.println("signup");
        assignClient(username);
    }
}
