package slovvik.pl.zad4b;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;
import static slovvik.pl.zad4b.MainActivity.FRUIT;
import static slovvik.pl.zad4b.MainActivity.MY_APP;


/**
 * A simple {@link Fragment} subclass.
 */
public class FruitListFragment extends Fragment implements ListView.OnItemClickListener {

    private static final String TAG = FruitListFragment.class.getSimpleName();

    private List<Fruit> fruitList;
    private Gson gson;

    public FruitListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fruit_list, container, false);
        fruitList = getFruitList();
        FruitAdapter fruitAdapter = new FruitAdapter(fruitList);
        ListView listView = (ListView) view.findViewById(R.id.fruitList);
        listView.setOnItemClickListener(this);
        listView.setAdapter(fruitAdapter);
        return view;
    }

    private List<Fruit> getFruitList() {
        List<Fruit> fruitList = new ArrayList<>();
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(MY_APP, MODE_PRIVATE);

        Map<String, ?> allFruits = sharedPreferences.getAll();
        gson = new Gson();
        for (String key : allFruits.keySet()) {
            Log.d(TAG, key);
            if (key.contains(FRUIT)) {
                String fruitJson = sharedPreferences.getString(key, "");
                Fruit fruit = gson.fromJson(fruitJson, Fruit.class);
                fruitList.add(fruit);
            }
        }
        return fruitList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        String fruitJson = gson.toJson(fruitList.get(position));
        bundle.putString(FRUIT, fruitJson);
        intent.putExtras(bundle);
        intent.setClass(parent.getContext(), FruitListItem.class);
        startActivity(intent);
    }
}
