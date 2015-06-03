package nl.bart_de_lange.android.ctour;


import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.*;
import android.widget.*;

public class MapFragment extends Fragment {
    private PopupWindow pw;


    private final int colorIns1 = 0xFF00FF00;
    private final int colorIns2 = 0xFFFF0000;
    private final int colorIns3 = 0xFF0000FF;
    private final int colorIns4 = 0xFF00FFFF;
    private final int colorPro1 = 0xFFFFFF00;
    private final int colorPro2 = 0xFFFF00FF;
    private final int colorPro3 = 0xFFFF5500;
    private final int colorPro4 = 0xFF5500ff;
    private final int colorPro5 = 0xFF99FF99;
    private final int colorTeacherRoom = 0xFFBB66BB;
    private final int colorGameLounge = 0xFF00FFF6;
    private final int colorOpr = 0xFF54864F;
    private final int colorCompict = 0xFFF08f54;
    private final int colorPlayBoxes = 0xFF1B1F67;
    private final int colorHal1 = 0xFF44DA63;
    private final int colorHal2 = 0xFFA66EBD;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                return onMapTouch(view, event);
            }
        });

        return view;
    }

    public boolean onMapTouch(View v, MotionEvent ev) {
        final int action = ev.getAction();
        final int evX = (int) ev.getX();
        final int evY = (int) ev.getY();

        ImageView imageView = (ImageView) v.findViewById(R.id.map_image);
        if (imageView == null) return false;

        if (action == MotionEvent.ACTION_UP) {
            int touchColor = getHotspotColor(R.id.map_image_areas, evX, evY);
            int tolerance = 0;

            switch (touchColor) {
                case colorIns1:
                    //INS1
                    loadMenuPopup(R.raw.ins1, R.string.header_ins1, R.string.info_ins1);
                    break;
                case colorIns2:
                    //INS2
                    loadMenuPopup(R.raw.ins1, R.string.header_ins2, R.string.info_ins2);
                    break;
                case colorIns3:
                    //INS3
                    loadMenuPopup(R.raw.ins1, R.string.header_ins3, R.string.info_ins3);
                    break;
                case colorIns4:
                    //INS4
                    loadMenuPopup(R.raw.ins1, R.string.header_ins4, R.string.info_ins4);
                    break;
                case colorPro1:
                    //PRO1
                    loadMenuPopup(R.raw.ins1, R.string.header_pro1, R.string.info_pro1);
                    break;
                case colorPro2:
                    //PRO2
                    loadMenuPopup(R.raw.ins1, R.string.header_pro2, R.string.info_pro2);
                    break;
                case colorPro3:
                    //PRO3
                    loadMenuPopup(R.raw.ins1, R.string.header_pro3, R.string.info_pro3);
                    break;
                case colorPro4:
                    //PRO4
                    loadMenuPopup(R.raw.ins1, R.string.header_pro4, R.string.info_pro4);
                    break;
                case colorPro5:
                    //PRO5
                    loadMenuPopup(R.raw.ins1, R.string.header_pro5, R.string.info_pro5);
                    break;
                case colorTeacherRoom:
                    //DocentenRuimte
                    loadMenuPopup(R.raw.ins1, R.string.header_teacherRoom, R.string.info_teacherRoom);
                    break;
                case colorGameLounge:
                    //GameLounge
                    loadMenuPopup(R.raw.ins1, R.string.header_gameLounge, R.string.info_gameLounge);
                    break;
                case colorOpr:
                    //OPR
                    loadMenuPopup(R.raw.ins1, R.string.header_opr, R.string.info_opr);
                    break;
                case colorCompict:
                    //Comp!CT
                    loadMenuPopup(R.raw.ins1, R.string.header_compict, R.string.info_compict);
                    break;
                case colorPlayBoxes:
                    //Spelkasten
                    loadMenuPopup(R.raw.ins1, R.string.header_playBoxes, R.string.info_playBoxes);
                    break;
                case colorHal1:
                    //Hal1
                    loadMenuPopup(R.raw.ins1, R.string.header_hal2, R.string.info_hal1);
                    break;
                case colorHal2:
                    //Hal2
                    loadMenuPopup(R.raw.ins1, R.string.header_hal2, R.string.info_hal2);
                    break;
            }
        }
        return true;
    }

    public int getHotspotColor(int hotspotId, int x, int y) {
        ImageView img = (ImageView) getActivity().findViewById(hotspotId);
        img.setDrawingCacheEnabled(true);
        Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
        img.setDrawingCacheEnabled(false);
        return hotspots.getPixel(x, y);
    }

    public void loadSphere(int sphere) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        SphereViewer activity = new SphereViewer(getActivity(), sphere);
        activity.startUp();
    }

    private void loadMenuPopup(final int photoSphere, final int headerString, final int infoString) {
        try {
            Log.v("MapFragment", "Showing menu popup for - " + getString(headerString));
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View popupLayout = inflater.inflate(R.layout.options_popup, (ViewGroup) getView().findViewById(R.id.popup_element));

            pw = new PopupWindow(popupLayout, -1, -1, true);
            pw.showAtLocation(popupLayout, Gravity.NO_GRAVITY, 0, 0);

            TextView headerText = (TextView) popupLayout.findViewById(R.id.pageHeader);
            headerText.setText(headerString);

            FrameLayout outerShadow = (FrameLayout) popupLayout.findViewById(R.id.outerShadow);
            outerShadow.setOnClickListener(cancelButtonClickListener);

            Button cancelButton = (Button) popupLayout.findViewById(R.id.closeButton);
            cancelButton.setOnClickListener(cancelButtonClickListener);

            Button loadInfoButton = (Button) popupLayout.findViewById(R.id.show_info);
            Button loadPanoramaButton = (Button) popupLayout.findViewById(R.id.show_panorama);

            loadInfoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //infoPopup Laden
                }
            });
            loadPanoramaButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadSphere(photoSphere);
                }
            });
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadInfoPopup(final int headerString, final int infoString) {
        try {
            Log.v("MapFragment", "Showing info popup for - " + getString(headerString));
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View popupLayout = inflater.inflate(R.layout.info_popup, (ViewGroup) getView().findViewById(R.id.popup_element));

            pw = new PopupWindow(popupLayout, -1, -1, true);
            pw.showAtLocation(popupLayout, Gravity.NO_GRAVITY, 0, 0);

            TextView headerText = (TextView) popupLayout.findViewById(R.id.pageHeader);
            headerText.setText(headerString);

            TextView infoText = (TextView) popupLayout.findViewById(R.id.infoText);
            headerText.setText(infoString);

            FrameLayout outerShadow = (FrameLayout) popupLayout.findViewById(R.id.outerShadow);
            outerShadow.setOnClickListener(cancelButtonClickListener);
            Button cancelButton = (Button) popupLayout.findViewById(R.id.closeButton);
            cancelButton.setOnClickListener(cancelButtonClickListener);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private View.OnClickListener cancelButtonClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            pw.dismiss();
        }
    };
}
