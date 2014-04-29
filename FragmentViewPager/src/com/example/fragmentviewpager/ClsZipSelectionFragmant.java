package com.example.fragmentviewpager;

import java.util.ArrayList;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.BE.ClsZipBE;
import com.example.location.ClsLocationTracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ClsZipSelectionFragmant extends Fragment {

private static View objView;

public static ClsZipSelectionFragmant newInstance(String text) {
	ClsZipSelectionFragmant pageFragment = new ClsZipSelectionFragmant();
   

    return pageFragment;
}
/**
 * Note that this may be null if the Google Play services APK is not
 * available.
 */

private static GoogleMap objMap;
private static Double dbLatitude, dbLongitude;

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
    if (container == null) {
        return null;
    }
    objView = (RelativeLayout) inflater.inflate(R.layout.page2_map_fragment, container, false);
      dbLatitude = 29.027167;
      dbLongitude = 76.466904;

           setUpMapIfNeeded(); // For setting up the MapFragment
    return objView;
}


/*
 *Method name: setUpMapIfNeeded
 *Purpose:Sets up the map if it is possible to do so 
 */
public static void setUpMapIfNeeded() {
    // Do a null check to confirm that we have not already instantiated the map.
    if (objMap == null) {
        // Try to obtain the map from the SupportMapFragment.
        objMap = ((SupportMapFragment) ClsLauncher.objFragmentManager
                .findFragmentById(R.id.page2map)).getMap();
        // Check if we were successful in obtaining the map.
        if (objMap != null)
            setUpMap();
    }
}

@Override
	public void setMenuVisibility(boolean menuVisible) {
		// TODO Auto-generated method stub
		super.setMenuVisibility(menuVisible);
	// check wheater the view is visible or not 
		if(menuVisible)
		{
			// visible
			/*
			 * for refeshing the markers on map based on zip list selction
			 */
	      if (objMap != null)
	            setUpMap();
		}
		
	}

/**
 * Method name: setUpMap
 * Purpose:
 * This is where we can add markers or lines, add listeners or move the
 * camera.
 * <p>
 * This should only be called once and when we are sure that {@link #objMap}
 * is not null.
 */
private static void setUpMap() {
    // For showing a move to my loction button
    objMap.setMyLocationEnabled(true);
    // For dropping a marker at a point on the Map
    objMap.clear();
    ArrayList<ClsZipBE> countryList = ClsCitiesListFragmant.objZipListAdapter.alZipList;
    for(int i=0;i<countryList.size();i++){
     ClsZipBE country = countryList.get(i);
     if(country.isSelected()){
    	 objMap.addMarker(new MarkerOptions().position(new LatLng(country.getLatitude(), country.getLongitude())).title(country.getCode()).snippet(country.getName()));
     }
    }
    // For zooming automatically to the Dropped PIN Location
    objMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(dbLatitude,
            dbLongitude), 5.0f));
}

@Override
public void onViewCreated(View view, Bundle savedInstanceState) {
    // TODO Auto-generated method stub
    if (objMap != null)
        setUpMap();

    if (objMap == null) {
        // Try to obtain the map from the SupportMapFragment.
        objMap = ((SupportMapFragment) ClsLauncher.objFragmentManager
                .findFragmentById(R.id.page2map)).getMap();
        // Check if we were successful in obtaining the map.
        if (objMap != null)
            setUpMap();
    }
}

/**** The mapfragment's id must be removed from the FragmentManager
 **** or else if the same it is passed on the next time then 
 **** app will crash ****/

@Override
public void onDestroyView() {
    super.onDestroyView();
    if (objMap != null) {
        ClsLauncher.objFragmentManager.beginTransaction()
            .remove(ClsLauncher.objFragmentManager.findFragmentById(R.id.page2map)).commit();
        objMap = null;
    }
}


}