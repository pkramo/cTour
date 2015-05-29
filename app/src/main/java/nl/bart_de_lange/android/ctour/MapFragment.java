package nl.bart_de_lange.android.ctour;


import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MapFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Button btnIns1 = (Button) getActivity().findViewById(R.id.btnIns1);
        Button btnIns2 = (Button) getActivity().findViewById(R.id.btnIns2);
        Button btnIns3 = (Button) getActivity().findViewById(R.id.btnIns3);
        Button btnIns4 = (Button) getActivity().findViewById(R.id.btnIns4);

        btnIns1.setOnClickListener(listener);
        btnIns2.setOnClickListener(listener);
        btnIns3.setOnClickListener(listener);
        btnIns4.setOnClickListener(listener);
        //Inflate the layout
        return inflater.inflate(R.layout.fragment_map, container, false);
    }
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), SphereActivity.class);
            Bundle b = new Bundle();
            switch (view.getId()) {
                case R.id.btnIns1:
                    b.putInt("photoSphere", R.raw.ins1);
                    break;
                case R.id.btnIns2:
                    b.putInt("photoSphere", R.raw.ins2);
                    break;
                case R.id.btnIns3:
                    b.putInt("photoSphere", R.raw.ins3);
                    break;
                case R.id.btnIns4:
                    b.putInt("photoSphere", R.raw.ins4);
                    break;
                default:
                    b.putInt("photoSphere", R.raw.ins1);
                    break;
            }
            intent.putExtras(b);
            startActivity(intent);
            getActivity().finish();
        }
    };
}
