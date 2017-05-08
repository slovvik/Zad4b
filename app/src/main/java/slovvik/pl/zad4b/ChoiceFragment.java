package slovvik.pl.zad4b;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChoiceFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    AppCompatActivity appCompatActivity;
    onChosenOptionListener chosenOptionListener;

    interface onChosenOptionListener {
        void onChosenOption(int option);
    }

    public ChoiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choice, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            appCompatActivity = (AppCompatActivity) context;
            chosenOptionListener = (onChosenOptionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(appCompatActivity.toString() + " musi implementować onChosenOptionListener");
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        chosenOptionListener.onChosenOption(checkedId);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((RadioGroup) getActivity().findViewById(R.id.Options)).setOnCheckedChangeListener(this);
    }
}
