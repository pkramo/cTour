package nl.bart_de_lange.android.ctour;


import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MapFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent photoActivityIntent = new Intent(getActivity(), SphereActivity.class);
        startActivity(photoActivityIntent);

        //Inflate the layout
        return inflater.inflate(R.layout.fragment_map, container, false);
    }
}
