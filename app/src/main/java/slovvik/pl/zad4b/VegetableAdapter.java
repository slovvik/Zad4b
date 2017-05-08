package slovvik.pl.zad4b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Bartek on 08.05.2017.
 */

public class VegetableAdapter extends BaseAdapter {

    private List<Vegetable> vegetableList;

    public VegetableAdapter(List<Vegetable> vegetableList) {
        this.vegetableList = vegetableList;
    }

    @Override
    public int getCount() {
        return vegetableList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        Context context = parent.getContext();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_list_row, null);
        }
        view = convertView;
        TextView rowFruitName = (TextView) view.findViewById(R.id.rowFruitName);
        rowFruitName.setText(vegetableList.get(position).getName());
        return view;
    }
}
