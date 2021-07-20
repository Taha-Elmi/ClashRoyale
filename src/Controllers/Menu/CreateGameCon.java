package Controllers.Menu;

import Main.Config;
import Models.Graphic.FXManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CreateGameCon {

    @FXML
    private VBox mainPane;

    @FXML
    private Label portLabel;

    Thread thread;
    private ServerSocket serverSocket;
    private Socket socket;

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
        portLabel.setText("The Game is on Port: " + port);
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = serverSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @FXML
    void backButtonOnAction(ActionEvent event) {
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.out.print("");
        }
        FXManager.goTo("playGame.fxml", Config.primaryStage);
    }
}
