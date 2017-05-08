package slovvik.pl.zad4b;

/**
 * Created by Bartek on 07.05.2017.
 */

public class Vegetable {

    private String name;
    private String taste;
    private String type;
    private String country;

    public Vegetable(String name, String taste, String type, String country) {
        this.name = name;
        this.taste = taste;
        this.type = type;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getTaste() {
        return taste;
    }

    public String getType() {
        return type;
    }

    public String getCountry() {
        return country;
    }
}
