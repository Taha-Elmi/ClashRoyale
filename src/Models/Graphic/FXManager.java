package Models.Graphic;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * A class to manage Graphical change in javafx.
 * like assigning a new scene to a stage or open a new window.
 */
public class FXManager {
    /**
     * assigning a new scene to the stage.
     * take fxml name and load it from a specific package.
     * then assign it to the stage.
     * @param fxmlName the fxml
     * @param stage    the stage
     */
    public static void goTo(String fxmlName,Stage stage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(FXManager.class.getResource("/Views/FXMLFiles/" + fxmlName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Open window.
     * take fxml name and load it from a specific package.
     * then open it on a new stage.
     * @param fxmlName the fxml name.
     */
    public static Stage openWindow(String fxmlName) {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(FXManager.class.getResource(
                    "/Views/FXMLFiles/" + fxmlName));
            stage.setScene(new Scene(root));
            stage.sizeToScene();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stage;
    }

    /**
     * Sets background.
     * take an image and a pane.
     * then set image as the background of the pane.
     * @param image the image
     * @param pane  the pane
     */
    public static void setBackground(Image image,Pane pane) {
        BackgroundImage backgroundimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1,1,
                        true, true,
                        false, false));
        pane.setBackground(new Background(backgroundimage));
    }

    /**
     * Gets image.
     * take an image name and load it from a specific package.
     * then return the image.
     * @param imageName the image name
     * @return the image
     */
    public static Image getImage(String imageName) {
        return new Image(FXManager.class.getResourceAsStream("/Views/Assets/Pictures" + imageName));
    }
}
