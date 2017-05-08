package slovvik.pl.zad4b;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class VegetableFragment extends Fragment {

    String[] strings = {"Poland", "Germany", "Italy"};


    public VegetableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vegetable, container, false);
        setSpinner(view);
        return view;
    }

    private void setSpinner(View view) {
        Spinner spinner = ((Spinner) view.findViewById(R.id.spinnerCountries));
        if (spinner != null) {
            ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(),
                    android.R.layout.simple_spinner_item,
                    strings);
            stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(stringArrayAdapter);
        }

    }

}
