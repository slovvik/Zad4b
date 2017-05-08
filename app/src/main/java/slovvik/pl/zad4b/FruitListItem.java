package slovvik.pl.zad4b;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;

import static slovvik.pl.zad4b.MainActivity.FRUIT;

public class FruitListItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_list_item);

        Bundle bundle = getIntent().getExtras();
        String fruitJson = bundle.getString(FRUIT);
        Gson gson = new Gson();
        Fruit fruit = gson.fromJson(fruitJson, Fruit.class);

        TextView fruitItemName = (TextView) findViewById(R.id.fruitItemName);
        TextView fruitItemColor = (TextView) findViewById(R.id.fruitItemColor);
        TextView fruitItemSize = (TextView) findViewById(R.id.fruitItemSize);
        TextView fruitItemWeight = (TextView) findViewById(R.id.fruitItemWeight);
        fruitItemName.setText("Fruit name: " + fruit.getName());
        fruitItemColor.setText("Fruit color: " + fruit.getColor());
        fruitItemSize.setText("Fruit size: " + fruit.getSize());
        fruitItemWeight.setText("Fruit weight: " + fruit.getWeight());
    }
}
