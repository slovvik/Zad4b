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
import static slovvik.pl.zad4b.MainActivity.VEGETABLE;


/**
 * A simple {@link Fragment} subclass.
 */
public class VegetableListFragment extends Fragment implements ListView.OnItemClickListener {

    private List<Vegetable> vegetableList;
    private Gson gson;

    public VegetableListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vegetable_list, container, false);
        vegetableList = getVegetableList();
        VegetableAdapter fruitAdapter = new VegetableAdapter(vegetableList);
        ListView listView = (ListView) view.findViewById(R.id.vegetableList);
        listView.setOnItemClickListener(this);
        listView.setAdapter(fruitAdapter);
        return view;
    }

    private List<Vegetable> getVegetableList() {
        List<Vegetable> vegetableList = new ArrayList<>();
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(MY_APP, MODE_PRIVATE);

        Map<String, ?> allFruits = sharedPreferences.getAll();
        gson = new Gson();
        for (String key : allFruits.keySet()) {
            if (key.contains(VEGETABLE)) {
                String vegetableJson = sharedPreferences.getString(key, "");
                Vegetable vegetable = gson.fromJson(vegetableJson, Vegetable.class);
                vegetableList.add(vegetable);
            }
        }
        return vegetableList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        String vegetableJson = gson.toJson(vegetableList.get(position));
        bundle.putString(VEGETABLE, vegetableJson);
        intent.putExtras(bundle);
        intent.setClass(parent.getContext(), VegetableListItem.class);
        startActivity(intent);
    }

}
