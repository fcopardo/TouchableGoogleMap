package com.grizzly.TouchableGoogleMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.SupportMapFragment;
import com.grizzly.R;

import java.util.HashMap;

/**
 * Created by Fco on 11/12/14.
 */
public class TouchableGoogleMapFragment extends SupportMapFragment {

    private String TAG = getClass().getSimpleName();
    private TouchableGoogleMapView TouchableMapView;

    public TouchableGoogleMapFragment(){
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            _paginadorListener = (onFragmentSendPaginacion) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(FunctionsMessageBuilding.
                    getListenerException(activity.getClass(), onFragmentSendPaginacion.class)
            );
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        TAG = getClass().getCanonicalName();
        View v = inflater.inflate(R.layout.touchable_mapview, container,
                false);

        this.initializeComponents(v, savedInstanceState);
        return v;
    }

    private void initializeComponents(View v, Bundle bundle){
        TouchableMapView = v
    }

}
