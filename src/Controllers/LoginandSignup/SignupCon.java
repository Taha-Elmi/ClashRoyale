package Controllers.LoginandSignup;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupCon extends Controller {

    @FXML
    private TextField confirmPasswordTextField;

    @Override
    public void initialize() {
        super.initialize();
        confirmPasswordTextField.styleProperty().bind(
                Bindings
                        .when(confirmPasswordTextField.focusedProperty())
                        .then("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);")
                        .otherwise("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);"));
    }
    @Override
    protected void loginButtonPressed() {
        Stage stage;
        Parent root = null;
        stage = (Stage) signupButton.getScene().getWindow();
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/FXMLFiles/login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    protected void signupButtonPressed() {
        System.out.println("signup");
    }
}
