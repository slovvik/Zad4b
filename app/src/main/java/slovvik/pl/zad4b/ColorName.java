package slovvik.pl.zad4b;

/**
 * Created by Bartek on 08.05.2017.
 */

public enum ColorName {

    RED("Red"),
    GREEN("Green"),
    BLUE("Blue");

    private String color;

    ColorName(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
