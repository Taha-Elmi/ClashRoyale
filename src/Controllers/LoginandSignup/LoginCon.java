package Controllers.LoginandSignup;

import Database.FileUtils;
import Main.Config;
import Models.Graphic.FXManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginCon extends Controller {
    @FXML
    private CheckBox rememberMeBox;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label warningLabel;
    public void initialize() {
        super.initialize();
        try {
            String username = FileUtils.rememberUsername();
            String password = FileUtils.rememberPassword();
            if (!(username == null || password == null)) {
                usernameTextField.setText(username);
                passwordField.setText(password);
                rememberMeBox.setSelected(true);
                Platform.runLater(() -> loginButton.requestFocus());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void loginButtonPressed() {
        try {
            loginProcess();
            if (rememberMeBox.isSelected()) {
                FileUtils.saveHim(usernameTextField.getText(), passwordField.getText());
            } else {
                FileUtils.doNotSaveHim();
            }
        } catch (IllegalArgumentException e) {
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        FXManager.goTo("MainMenu.fxml", (Stage) loginButton.getScene().getWindow());
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
