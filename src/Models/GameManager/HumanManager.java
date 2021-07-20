package Models.GameManager;

import Main.Config;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class HumanManager implements Manager ,Runnable{
    private Player player;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public HumanManager(Player player, Socket socket) {
        this.player = player;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            Config.unknownInputException();
        }
    }

    @Override
    public void waitForAction() {

    }

    @Override
    public void action() {

    }

    @Override
    public void run() {

    }

    public void sendData(DataPackage dataPackage) {

    }
}

class DataPackage implements Serializable {

}
