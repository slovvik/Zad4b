package slovvik.pl.zad4b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import static slovvik.pl.zad4b.MainActivity.FRUIT;
import static slovvik.pl.zad4b.MainActivity.VEGETABLE;

public class VegetableListItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetable_list_iteam);

        Bundle bundle = getIntent().getExtras();
        String vegetableJson = bundle.getString(VEGETABLE);
        Gson gson = new Gson();
        Vegetable vegetable = gson.fromJson(vegetableJson, Vegetable.class);

        TextView vegetableItemName = (TextView) findViewById(R.id.vegetableItemName);
        TextView vegetableItemColor = (TextView) findViewById(R.id.vegetableItemColor);
        TextView vegetableItemSize = (TextView) findViewById(R.id.vegetableItemSize);
        TextView vegetableItemWeight = (TextView) findViewById(R.id.vegetableItemWeight);
        vegetableItemName.setText("Vegetable name: " + vegetable.getName());
        vegetableItemColor.setText("Vegetable type: " + vegetable.getType());
        vegetableItemSize.setText("Vegetable taste: " + vegetable.getTaste());
        vegetableItemWeight.setText("Vegetable country: " + vegetable.getCountry());
    }
}
