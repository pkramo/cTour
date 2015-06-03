package nl.bart_de_lange.android.ctour;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;


public class MainActivity extends ActionBarActivity implements DrawerCallbacks {

    private Toolbar mToolbar;
    private Nav_DrawerFragment mNavigationNavDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics metrics =  getResources().getDisplayMetrics();
        float px = 4 * (metrics.densityDpi / 160f);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setElevation(px);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                }
                return false;
            }
        });

        mNavigationNavDrawerFragment = (Nav_DrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
        mNavigationNavDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                //Plattegrond
                MapFragment mapFragment = new MapFragment();
                transaction.replace(R.id.fragment_container, mapFragment);
                transaction.commit();
                break;
            case 1:
                //Algemene Informatie
                InfoFragment infoFragment = new InfoFragment();
                transaction.replace(R.id.fragment_container, infoFragment);
                transaction.commit();
                break;
            case 2:
                //Contact
                ContactFragment contactFragment = new ContactFragment();
                transaction.replace(R.id.fragment_container, contactFragment);
                transaction.commit();
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
