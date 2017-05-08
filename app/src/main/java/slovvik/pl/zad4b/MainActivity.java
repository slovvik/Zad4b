package slovvik.pl.zad4b;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener, ChoiceFragment.onChosenOptionListener {

    public static final String MY_APP = "pl.slovvik.zad4b";
    public static final String FRUIT = "fruit";
    public static final String VEGETABLE = "vegetable";

    WelcomeFragment welcomeFragment;
    InputFragment inputFragment;
    FruitListFragment fruitListFragment;
    VegetableListFragment vegetableListFragment;

    FruitFragment fruitFragment;
    VegetableFragment vegetableFragment;

    FragmentTransaction fragmentTransaction;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(MY_APP, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        welcomeFragment = new WelcomeFragment();
        inputFragment = new InputFragment();
        fruitListFragment = new FruitListFragment();
        vegetableListFragment = new VegetableListFragment();

        fruitFragment = new FruitFragment();
        vegetableFragment = new VegetableFragment();

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, welcomeFragment);
        fragmentTransaction.detach(welcomeFragment);
        fragmentTransaction.add(R.id.container, inputFragment);
        fragmentTransaction.detach(inputFragment);
        fragmentTransaction.add(R.id.container, fruitListFragment);
        fragmentTransaction.detach(fruitListFragment);
        fragmentTransaction.add(R.id.container, vegetableListFragment);
        fragmentTransaction.detach(vegetableListFragment);

        fragmentTransaction.add(R.id.showItems, fruitFragment);
        fragmentTransaction.detach(fruitFragment);
        fragmentTransaction.add(R.id.showItems, vegetableFragment);
        fragmentTransaction.detach(vegetableFragment);
        fragmentTransaction.commit();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab1 = actionBar.newTab().setText("Welcome").setTabListener(this);
        actionBar.addTab(tab1);
        ActionBar.Tab tab2 = actionBar.newTab().setText("Choice").setTabListener(this);
        actionBar.addTab(tab2);
        ActionBar.Tab tab3 = actionBar.newTab().setText("Fruit list").setTabListener(this);
        actionBar.addTab(tab3);
        ActionBar.Tab tab4 = actionBar.newTab().setText("Vegetable list").setTabListener(this);
        actionBar.addTab(tab4);

    }

    public void saveVegetable(View view) {
        TextView vegetableName = (TextView) findViewById(R.id.vegetableName);
        RadioGroup vegetableGroup = (RadioGroup) findViewById(R.id.fruitColor);
        RadioButton vegetableType = (RadioButton) findViewById(vegetableGroup.getCheckedRadioButtonId());
        CheckBox tasteSweet = (CheckBox) findViewById(R.id.tasteSweet);
        CheckBox tasteSour = (CheckBox) findViewById(R.id.tasteSour);
        CheckBox tasteBitter = (CheckBox) findViewById(R.id.tasteBitter);
        Spinner country = (Spinner) findViewById(R.id.spinnerCountries);
        String vegetableTaste = "";
        if (tasteSweet.isChecked()) vegetableTaste += tasteSweet.getText().toString() + " ";
        if (tasteSour.isChecked()) vegetableTaste += tasteSour.getText().toString() + " ";
        if (tasteBitter.isChecked()) vegetableTaste += tasteBitter.getText().toString();

        Vegetable vegetable = new Vegetable(vegetableName.getText().toString(),
                vegetableTaste,
                vegetableType.getText().toString(),
                country.getSelectedItem().toString());

        Gson vegetableGson = new Gson();
        String vegetableJson = vegetableGson.toJson(vegetable);
        editor.putString(vegetableName.getText().toString() + " " + VEGETABLE, vegetableJson);
        if (!editor.commit()) {
            Toast.makeText(getApplicationContext()
                    , "Can not save vegetable: " + vegetableName.getText().toString()
                    , Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext()
                    , "Saved vegetable: " + vegetableName.getText().toString()
                    , Toast.LENGTH_SHORT).show();
        }
    }

    public void saveFruit(View view) {
        TextView fruitName = (TextView) findViewById(R.id.fruitName);
        RadioGroup fruitColorGroup = (RadioGroup) findViewById(R.id.fruitColor);
        RadioButton fruitColor = (RadioButton) findViewById(fruitColorGroup.getCheckedRadioButtonId());
        TextView fruitSize = (TextView) findViewById(R.id.fruitSize);
        TextView fruitWeight = (TextView) findViewById(R.id.fruitWeight);
        Fruit fruit = new Fruit(fruitName.getText().toString(),Integer.valueOf(fruitSize.getText().toString()), Integer.valueOf(fruitWeight.getText().toString()));
        fruit.setColor(fruitColor.getText().toString());
        Gson fruitGson = new Gson();
        String fruitJson = fruitGson.toJson(fruit);
        editor.putString(fruitName.getText().toString() + " " + FRUIT, fruitJson);
        if (!editor.commit()) {
            Toast.makeText(getApplicationContext()
                    , "Can not save fruit: " + fruitName.getText().toString()
                    , Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext()
                    , "Saved fruit: " + fruitName.getText().toString()
                    , Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        switch (tab.getPosition()) {
            case 0:
                ft.attach(welcomeFragment);
                break;
            case 1:
                ft.attach(inputFragment);
                break;
            case 2:
                ft.attach(fruitListFragment);
                break;
            case 3:
                ft.attach(vegetableListFragment);
                break;
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        switch (tab.getPosition()) {
            case 0:
                ft.detach(welcomeFragment);
                break;
            case 1:
                ft.detach(inputFragment);
                break;
            case 2:
                ft.detach(fruitListFragment);
                break;
            case 3:
                ft.detach(vegetableListFragment);
                break;
        }
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onChosenOption(int option) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.detach(fruitFragment);
        fragmentTransaction.detach(vegetableFragment);

        switch (option) {
            case R.id.Fruit:
                fragmentTransaction.attach(fruitFragment);
                break;
            case R.id.Vegetable:
                fragmentTransaction.attach(vegetableFragment);
                break;
        }
        fragmentTransaction.commit();
    }
}
