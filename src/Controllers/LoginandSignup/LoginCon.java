package Controllers.LoginandSignup;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginCon extends Controller {


    @Override
    protected void loginButtonPressed() {
        System.out.println("login");
    }

    @Override
    protected void signupButtonPressed() {
        Stage stage;
        Parent root = null;
        stage = (Stage) signupButton.getScene().getWindow();
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/FXMLFiles/signup.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
