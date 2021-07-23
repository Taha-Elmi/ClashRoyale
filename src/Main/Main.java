package Main;

import Database.SQLManager;
import Models.Graphic.FXManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Main.
 */
public class Main extends Application {

    //test commit
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/FXMLFiles/login.fxml"));
        primaryStage.setTitle("Clash Royale");
        primaryStage.setResizable(false);
        Scene scene = new Scene(root,400,520);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(FXManager.getImage("/Icons/mainicon.jpg"));
        Config.primaryStage = primaryStage;
        primaryStage.show();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SQLManager.connectToDatabase();
        launch(args);
    }
}
