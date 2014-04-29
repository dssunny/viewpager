package com.example.fragmentviewpager;

/*
 * Created by : Deepak Sharma
 *  
 */


import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;

public class ClsLauncher extends FragmentActivity {

	static ViewPager objViewPager;
	ClsViewPagerAdapter objFragmentPagerAdapter;
	static FragmentManager objFragmentManager;

	ArrayList<Fragment> alOfFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/*
		 * Creating an array list of the fragmants which we need to add on view
		 * pager
		 */

		alOfFragment = new ArrayList<Fragment>();
		alOfFragment.add(ClsCitiesListFragmant.newInstance("Fragment1")); // fragment
																	// contains
																	// a list of
																	// famous
																	// cities
		alOfFragment.add(ClsZipSelectionFragmant.newInstance("Fragment2"));// fragment
																	// contains
																	// a map
																	// that show
																	// pin on
																	// selected
																	// latitude
																	// ,longitude
																	// on
																	// fragment1
		alOfFragment.add(ClsCurrentLocationFragment.newInstance("Fragment3"));// fragment
																	// contains
																	// a map
																	// that
																	// shows a
																	// pin image
																	// on
																	// current
																	// location
		alOfFragment.add(ClsContactListFragmant.newInstance("Fragment4")); // fragment
																	// contains
																	// a list
																	// showing
																	// the
																	// contacts
																	// of your
																	// phone

		objFragmentManager = getSupportFragmentManager();

		objViewPager = (ViewPager) findViewById(R.id.view_pager);

		objFragmentPagerAdapter = new ClsViewPagerAdapter(objFragmentManager,
				alOfFragment);
		objViewPager.setAdapter(objFragmentPagerAdapter);

		objViewPager.setOffscreenPageLimit(0);
	

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private static class ClsViewPagerAdapter extends FragmentPagerAdapter {
		ArrayList<Fragment> alOfFragment;

		public ClsViewPagerAdapter(FragmentManager fm,
				ArrayList<Fragment> alOfFragment) {
			super(fm);
			this.alOfFragment = alOfFragment;
		}

		@Override
		public Fragment getItem(int index) {

			return alOfFragment.get(index);
		}

		@Override
		public int getCount() {
			return alOfFragment.size();
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		android.os.Process.killProcess(android.os.Process.myPid());
	}
}
