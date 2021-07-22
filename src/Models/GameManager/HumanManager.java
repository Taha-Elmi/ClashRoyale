package Models.GameManager;

import Controllers.GameCon;
import Main.Config;
import Models.Cards.Card;
import Models.Client;
import javafx.application.Platform;
import javafx.geometry.Point2D;

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
    private Thread listener;

    public HumanManager(Socket socket, NetworkClient networkClient) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    objectInputStream = new ObjectInputStream(socket.getInputStream());
                    objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    Client client;
                    if (networkClient == NetworkClient.JOIN) {
                        Thread.sleep(100);
                        objectOutputStream.writeObject(Config.client);
                        client = ((Client) objectInputStream.readObject());
                    } else {
                        client = ((Client) objectInputStream.readObject());
                        objectOutputStream.writeObject(Config.client);
                    }

                    name = client.getName();
                    level = client.getLevel();
                    player = new Player(client.getDeckCards());

                    listener = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (true) {
                                try {
                                    dataPackage = ((DataPackage) objectInputStream.readObject());
                                } catch (IOException | ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                    listener.start();

                } catch (IOException | ClassNotFoundException | InterruptedException e) {
                    e.printStackTrace();
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
        Card card = dataPackage.getCard();
        Game.getInstance().getPlayer2().setElixirs(Game.getInstance().getPlayer2().getElixirs() - card.getCost());

        Point2D point2D = DataPackage.convert(dataPackage.getPoint2D());
        Game.getInstance().bornCard(card, point2D, point2D, GameCon.getInstance().getBoardPane(), 2);
        Game.getInstance().playCardPlayer2(card);
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

    public Thread getListener() {
        return listener;
    }

    public void sendData(Card card, Point2D point2D) {
        DataPackage dataPackageToSend = new DataPackage(card, point2D);
        try {
            objectOutputStream.writeObject(dataPackageToSend);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class DataPackage implements Serializable {
    private Card card;
    private Point2D point2D;

    public DataPackage(Card card, Point2D point2D) {
        this.card = card;
        this.point2D = point2D;
    }

    public static Point2D convert(Point2D point2D) {
        double x = GameCon.getInstance().getBoardPane().getPrefWidth() - point2D.getX();
        double y = GameCon.getInstance().getBoardPane().getPrefHeight() - point2D.getY();
        return new Point2D(x, y);
    }

    public Card getCard() {
        return card;
    }

    public Point2D getPoint2D() {
        return point2D;
    }
}
