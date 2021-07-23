package Controllers.Menu;

import Main.Config;
import Models.GameManager.*;
import Models.Graphic.FXManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Create Game controller
 */
public class CreateGameCon {

    @FXML
    private VBox mainPane;

    @FXML
    private Label portLabel;

    private Thread thread;
    private ServerSocket serverSocket;
    private Socket socket;

    /**
     * Initialize.
     */
    public void initialize() {
        FXManager.setBackground(FXManager.getImage("/BackGrounds/PlayGame.jpg"), mainPane);

        int port = 1234;
        boolean remainInLoop = true;
        while (remainInLoop) {
            try {
                serverSocket = new ServerSocket(1234);
                remainInLoop = false;
            } catch (IOException e) {
                port++;
            }
        }

        try {
            portLabel.setText("Other Player Can Access the Game Via:\n" +
                    "IP: " + Inet4Address.getLocalHost().getHostAddress() + "\tPORT: " + port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = serverSocket.accept();
                    HumanManager humanManager = new HumanManager(socket, NetworkClient.CREATE);
                    new Game(new Player(Config.client.getDeckCards()), humanManager.getPlayer(), GameMode.MULTI, humanManager);
                    Config.mediaPlayer.stop();
                    FXManager.goTo("game.fxml",Config.primaryStage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    /**
     * back button handler
     * @param event the event
     */
    @FXML
    void backButtonOnAction(ActionEvent event) {
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.out.print("");
        }
        FXManager.goTo("1v1.fxml", Config.primaryStage);
    }
}
