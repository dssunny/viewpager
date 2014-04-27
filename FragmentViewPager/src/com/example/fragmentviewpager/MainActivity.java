package com.example.fragmentviewpager;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;



public class MainActivity extends FragmentActivity implements
		OnPageChangeListener {

	static ViewPager objViewPager;
	MyFragmentPagerAdapter objFragmentPagerAdapter;
	static FragmentManager objFragmentManager;

	ArrayList<Fragment> alOfFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		alOfFragment = new ArrayList<Fragment>();
		alOfFragment.add(Page1Fragment.newInstance("Fragment1"));
		alOfFragment.add(Page2MapFragment.newInstance("Fragment2"));
		alOfFragment.add(Page3MapFragment.newInstance("Fragment3"));
		alOfFragment.add(Page4Fragment.newInstance("Fragment4"));

		objFragmentManager = getSupportFragmentManager();

		objViewPager = (ViewPager) findViewById(R.id.view_pager);
		objFragmentPagerAdapter = new MyFragmentPagerAdapter(
				objFragmentManager, alOfFragment);
		objViewPager.setAdapter(objFragmentPagerAdapter);

		objViewPager.setOffscreenPageLimit(0);
		objViewPager.setOnPageChangeListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private static class MyFragmentPagerAdapter extends FragmentPagerAdapter {
		ArrayList<Fragment> alOfFragment;

		public MyFragmentPagerAdapter(FragmentManager fm,
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
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		android.os.Process.killProcess(android.os.Process.myPid());
	}
}
