package slovvik.pl.zad4b;

import static slovvik.pl.zad4b.ColorName.BLUE;
import static slovvik.pl.zad4b.ColorName.GREEN;
import static slovvik.pl.zad4b.ColorName.RED;

/**
 * Created by Bartek on 07.05.2017.
 */

public class Fruit {

    private String name;
    private ColorName color;
    private int size;
    private int weight;

    public Fruit(String name, ColorName color, int size, int weight) {
        this.name = name;
        this.color = color;
        this.weight = weight;
        this.size = size;
    }

    public Fruit(String name, int size, int weight) {
        this.name = name;
        this.weight = weight;
        this.size = size;
    }

    public void setColor(String color) {
        switch (color) {
            case "Red":
                this.color = RED;
                break;
            case "Blue":
                this.color = BLUE;
                break;
            case "Green":
                this.color = GREEN;
                break;
        }
    }

    public String getName() {
        return name;
    }

    public ColorName getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }

    public int getSize() {
        return size;
    }
}
