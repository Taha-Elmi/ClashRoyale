package Models.GameManager;

public class Location {
    private final int row;
    private final int column;

    public Location(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Location) {
            Location loc = (Location) obj;
            return getRow() == loc.getRow() && getColumn() == loc.getColumn();
        } else
            return false;
    }
}