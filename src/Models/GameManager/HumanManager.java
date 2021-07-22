package Models.GameManager;

import Main.Config;
import Models.Client;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class HumanManager implements Manager ,Runnable{
    private String name;
    private int level;
    private Player player;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private DataPackage dataPackage;

    public HumanManager(Socket socket, NetworkClient networkClient) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    objectInputStream = new ObjectInputStream(socket.getInputStream());
                    objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    Client client;
                    if (networkClient == NetworkClient.JOIN) {
                        objectOutputStream.writeObject(Config.client);
                        client = ((Client) objectInputStream.readObject());
                    } else {
                        client = ((Client) objectInputStream.readObject());
                        objectOutputStream.writeObject(Config.client);
                    }

                    name = client.getName();
                    level = client.getLevel();
                    player = new Player(client.getDeckCards());

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (true) {
                                try {
                                    dataPackage = ((DataPackage) objectInputStream.readObject());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });

                } catch (IOException | ClassNotFoundException e) {
                    Config.unknownInputException();
                }
            }
        });
    }

    @Override
    public void waitForAction() {

    }

    @Override
    public void action() {
        if (dataPackage == null)
            return;

        dataPackage = null;
    }

    @Override
    public void run() {

    }

    @Override
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    public void sendData(DataPackage dataPackage) {

    }
}

class DataPackage implements Serializable {

}
