package com.example.fragmentviewpager;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.gps.GPSTracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Page3MapFragment extends Fragment {

	private static View objView;

	public static Page3MapFragment newInstance(String text) {
		Page3MapFragment pageFragment = new Page3MapFragment();

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
		objView = (RelativeLayout) inflater.inflate(
				R.layout.page3_map_fragment, container, false);
		// Passing harcoded values for latitude & longitude. Please change as
		// per your need. This is just used to drop a Marker on the Map

		Location objLocation = GPSTracker.getInstance(getActivity())
				.getLocation();
		
		
		if (objLocation != null) {

			dbLatitude = objLocation.getLatitude();
			dbLongitude = objLocation.getLongitude();

			setUpMapIfNeeded(); // For setting up the MapFragment
		}
		return objView;
	}

	/***** Sets up the map if it is possible to do so *****/
	public static void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the
		// map.
		if (objMap == null) {
			// Try to obtain the map from the SupportMapFragment.
			objMap = ((SupportMapFragment) MainActivity.objFragmentManager
					.findFragmentById(R.id.page3map)).getMap();
			// Check if we were successful in obtaining the map.
			if (objMap != null)
				setUpMap();
		}
	}

	/**
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
		objMap.addMarker(new MarkerOptions()
				.position(new LatLng(dbLatitude, dbLongitude)).title("My Home")
				.snippet("Home Address"));
		// For zooming automatically to the Dropped PIN Location
		objMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
				dbLatitude, dbLongitude), 12.0f));
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (objMap != null)
			setUpMap();

		if (objMap == null) {
			// Try to obtain the map from the SupportMapFragment.
			objMap = ((SupportMapFragment) MainActivity.objFragmentManager
					.findFragmentById(R.id.page3map)).getMap();
			// Check if we were successful in obtaining the map.
			if (objMap != null)
				setUpMap();
		}
	}

	/****
	 * The mapfragment's id must be removed from the FragmentManager or else if
	 * the same it is passed on the next time then app will crash
	 ****/
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (objMap != null) {
			MainActivity.objFragmentManager
					.beginTransaction()
					.remove(MainActivity.objFragmentManager
							.findFragmentById(R.id.page3map)).commit();
			objMap = null;
		}
	}
}