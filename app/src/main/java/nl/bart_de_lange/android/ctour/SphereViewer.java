package nl.bart_de_lange.android.ctour;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.panorama.Panorama;
import com.google.android.gms.panorama.PanoramaApi.PanoramaResult;

public class SphereViewer implements ConnectionCallbacks,
        OnConnectionFailedListener {

    public static final String TAG = SphereViewer.class.getSimpleName();
    private GoogleApiClient mClient;
    private int PhotoSphere;
    private Intent viewerIntent;
    public Activity activity;


    public SphereViewer(Activity act, int photo)
    {
        this.activity = act;
        this.PhotoSphere = photo;
    }

    public void startUp() {
        mClient = new GoogleApiClient.Builder(activity.getApplicationContext(), this, this).addApi(Panorama.API).build();
        mClient.connect();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        Uri uri = Uri.parse("android.resource://" + activity.getPackageName() + "/" + PhotoSphere);
        Panorama.PanoramaApi.loadPanoramaInfo(mClient, uri).setResultCallback(new ResultCallback<PanoramaResult>() {
            @Override
            public void onResult(PanoramaResult result) {
                if (result.getStatus().isSuccess()) {
                    viewerIntent = result.getViewerIntent();
                    Log.i(TAG, "found viewerIntent: " + viewerIntent);
                    if (viewerIntent != null) {
                        activity.startActivityForResult(viewerIntent, 1);
                    }
                } else {
                    Log.e(TAG, "error: " + result);
                }
            }
        });
    }

    @Override
    public void onConnectionSuspended(int cause) {
        Log.i(TAG, "connection suspended: " + cause);
    }

    @Override
    public void onConnectionFailed(ConnectionResult status) {
        Log.e(TAG, "connection failed: " + status);
    }
}