package util;

import javafx.scene.control.Button;

public class ButtonPerso {
    private Button button;
    private Coord coord;

    public ButtonPerso(Button button, Coord coord) {
        this.button = button;
        this.coord = coord;
    }

    public Button getButton() {
        return button;
    }

    public Coord getCoord() {
        return coord;
    }
}
