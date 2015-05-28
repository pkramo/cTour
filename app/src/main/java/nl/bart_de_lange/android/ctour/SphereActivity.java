package nl.bart_de_lange.android.ctour;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.panorama.Panorama;
import com.google.android.gms.panorama.PanoramaApi.PanoramaResult;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class SphereActivity extends Activity implements ConnectionCallbacks,
        OnConnectionFailedListener {

    public static final String TAG = SphereActivity.class.getSimpleName();
    private GoogleApiClient mClient;
    private int PhotoSphere;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClient = new GoogleApiClient.Builder(this, this, this)
                .addApi(Panorama.API)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        mClient.connect();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + PhotoSphere);
        Panorama.PanoramaApi.loadPanoramaInfo(mClient, uri).setResultCallback(new ResultCallback<PanoramaResult>() {
            @Override
            public void onResult(PanoramaResult result) {
                if (result.getStatus().isSuccess()) {
                    Intent viewerIntent = result.getViewerIntent();
                    Log.i(TAG, "found viewerIntent: " + viewerIntent);
                    if (viewerIntent != null) {
                        startActivity(viewerIntent);
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
        // TODO fill in
    }

    @Override
    public void onStop() {
        super.onStop();
        mClient.disconnect();
    }

}