package GameManager;

public class Board {
    private final static int length = 100;
    private final static int width = 40;
    private Location[][] locations = new Location[length][width];

    public static int getLength() {
        return length;
    }

    public static int getWidth() {
        return width;
    }

    public Location[][] getLocations() {
        return locations;
    }
}
