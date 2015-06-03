package nl.bart_de_lange.android.ctour;


import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MapFragment extends Fragment {
    private PopupWindow pw;


    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        Button btnIns1 = (Button) view.findViewById(R.id.btnIns1);
        Button btnIns2 = (Button) view.findViewById(R.id.btnIns2);
        Button btnIns3 = (Button) view.findViewById(R.id.btnIns3);
        Button btnIns4 = (Button) view.findViewById(R.id.btnIns4);

        btnIns1.setOnClickListener(listener);
        btnIns2.setOnClickListener(listener);
        btnIns3.setOnClickListener(listener);
        btnIns4.setOnClickListener(listener);

        return view;
    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            final SphereActivity[] activity = new SphereActivity[1];
            PopupMenu popup = new PopupMenu(getActivity(), view);
            popup.getMenuInflater().inflate(R.menu.map_popup, popup.getMenu());

            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    switch (view.getId()) {
                        case R.id.btnIns1:
                            if (item.getItemId() == R.id.show_panorama) {
                                activity[0] = new SphereActivity(getActivity(), R.raw.ins1);
                                activity[0].startUp();
                            } else if (item.getItemId() == R.id.show_info) {
                                initiatePopupWindow(R.string.header_ins1, R.string.info_ins1);
                            }
                            break;
                        case R.id.btnIns2:
                            if (item.getItemId() == R.id.show_panorama) {
                                activity[0] = new SphereActivity(getActivity(), R.raw.ins2);
                                activity[0].startUp();
                            } else if (item.getItemId() == R.id.show_info) {
                                initiatePopupWindow(R.string.header_ins2, R.string.info_ins2);
                            }
                            break;
                        case R.id.btnIns3:
                            if (item.getItemId() == R.id.show_panorama) {
                                activity[0] = new SphereActivity(getActivity(), R.raw.ins3);
                                activity[0].startUp();
                            } else if (item.getItemId() == R.id.show_info) {
                                initiatePopupWindow(R.string.header_ins3, R.string.info_ins3);
                            }
                            break;
                        case R.id.btnIns4:
                            if (item.getItemId() == R.id.show_panorama) {
                                activity[0] = new SphereActivity(getActivity(), R.raw.ins4);
                                activity[0].startUp();
                            } else if (item.getItemId() == R.id.show_info) {
                                initiatePopupWindow(R.string.header_ins4, R.string.info_ins4);
                            }
                            break;
                    }
                    return true;
                }
            });
            popup.show();
        }
    };

    private void initiatePopupWindow(int hdrText, int infoText) {
        try {
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup_layout, (ViewGroup) getView().findViewById(R.id.popup_element));

            Display display = getActivity().getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            pw = new PopupWindow(layout, size.x, size.y, true);
            int dpX = (int) (getResources().getDimension(R.dimen.abc_action_bar_default_height_material) / getResources().getDisplayMetrics().density);
            int dpY = (int) (getResources().getDimension(R.dimen.abc_action_bar_default_height_material) / getResources().getDisplayMetrics().density);
            pw.showAtLocation(layout, Gravity.NO_GRAVITY, dpX, (dpY * 2));

            TextView headerText = (TextView) layout.findViewById(R.id.pageHeader);
            TextView informationText = (TextView) layout.findViewById(R.id.infoText);
            headerText.setText(hdrText);
            informationText.setText(infoText);
            Button cancelButton = (Button) layout.findViewById(R.id.closeButton);
            cancelButton.setOnClickListener(cancel_button_click_listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            pw.dismiss();
        }
    };
}
