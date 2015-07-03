package com.grizzly.TouchableGoogleMap;


import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.CameraPosition;
import com.grizzly.R;

/**
 * Created by Fco Pardo on 12/12/14.
 * A custom view intended to be used instead of the google's MapView. it includes
 * a custom listener to be fired when the user releases the map.
 */
public class TouchableGoogleMapView extends TouchableWrapper {

    /**
     * Class members
     */
    private GoogleMap map;
    private MapView mapView;
    private OnTouchableMapReleased mapReleased;

    public TouchableGoogleMapView(Context context) {
        super(context);
        initComponents(context);
    }

    public TouchableGoogleMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initComponents(context);
    }

    public TouchableGoogleMapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initComponents(context);
    }

    private void initComponents(Context context){

        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(infService);
        inflater.inflate(R.layout.touchable_mapview, this, true);
        mapView = (MapView)findViewById(R.id.mapView);
    }

    public void initializeMap(Bundle savedInstanceState, Context context){

        mapView.onCreate(savedInstanceState);
        mapView.onResume();// needed to get the _map to display immediately
        try {
            MapsInitializer.initialize(context.getApplicationContext());
            if(map==null){
                map = mapView.getMap();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * A custom listener that fires when the camera position is changed
     * and the map is not being touched in any way.
     * @param listener
     */
    public void setOnTouchableMapReleased(final OnTouchableMapReleased listener){
        mapReleased = listener;
        map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                if (isContentTouched()) {
                    mapReleased.onMapReleased(cameraPosition);
                }
            }
        });
    }

    /**
     * Returns the embedded google map.
     * @return
     */
    public GoogleMap getMap() {
        return map;
    }

    /**
     * Returns the embedded MapView.
     * @return
     */
    public MapView getMapView() {
        return mapView;
    }

    /**
     * Setter for the inner google map.
     * @param map
     */
    public void setGoogleMap(GoogleMap map){
        this.map = map;
    }


}
