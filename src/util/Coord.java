package util;

public class Coord {
        private int x;
        private int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coord coord = (Coord) o;
        return this.x == coord.x && this.y == coord.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
