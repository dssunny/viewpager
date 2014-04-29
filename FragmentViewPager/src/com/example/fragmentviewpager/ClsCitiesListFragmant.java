package com.example.fragmentviewpager;

/*
 * Created by: Deepak Sharma
 */
import java.util.ArrayList;

import com.example.BE.ClsZipBE;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ClsCitiesListFragmant extends Fragment {


static ClsZipListAdapter objZipListAdapter;

/*
 * Method name: newInstance
 * Argument1: type(String),purpose: to give the name to the fragment
 * Return type:  type(ClsCitiesListFragmant) , purpose: return the instance of the ClsCitiesListFragmant
 */
public static ClsCitiesListFragmant newInstance(String text) {
    ClsCitiesListFragmant pageFragment = new ClsCitiesListFragmant();
   
    return pageFragment;
}

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)   {
    View view = inflater.inflate(R.layout.listfragment, container, false);
   
    ListView lstViewOfZip= (ListView)view.findViewById(R.id.listView1);
    
    /*
     * Entering dummy data for list
     */
    ArrayList<ClsZipBE> alZipList = new ArrayList<ClsZipBE>();
    ClsZipBE objZipBE = new ClsZipBE("AFG","Agra",true,27.175015,78.042155);
    alZipList.add(objZipBE);
    objZipBE = new ClsZipBE("LKW","Lucknow",true,26.698776,81.390714);
    alZipList.add(objZipBE);
    objZipBE = new ClsZipBE("JPR","Jaipur",true,26.777268,75.897550);
    alZipList.add(objZipBE);
    objZipBE = new ClsZipBE("PTN","Patna",true,25.554606,85.213956);
    alZipList.add(objZipBE);
    objZipBE = new ClsZipBE("JKD","Jharkhand",true,23.918253,84.159269);
    alZipList.add(objZipBE);
    objZipBE = new ClsZipBE("SNG","Srinagar",true,33.899849,76.161222);
    alZipList.add(objZipBE);
    objZipBE = new ClsZipBE("ADM","Ahemdabad",true,22.991037,72.865323);
    alZipList.add(objZipBE);
    objZipBE = new ClsZipBE("JSI","Jhasi",true,25.304304,78.629150);
    alZipList.add(objZipBE);
    objZipBE = new ClsZipBE("NSK","Nasik",true,19.890723,73.894043);
    alZipList.add(objZipBE);
    objZipBE = new ClsZipBE("MBI","Mumbai",true,19.020577,72.971191);
    alZipList.add(objZipBE);
    objZipBE = new ClsZipBE("ODS","Odissa",true,20.468189,84.440918);
    alZipList.add(objZipBE);
    objZipBE = new ClsZipBE("GOA","Goa",true,15.241790,73.937988);
    alZipList.add(objZipBE);
    objZipBE = new ClsZipBE("HDB","Chennai",true,12.983148,80.222168);
    alZipList.add(objZipBE);
    objZipBE = new ClsZipBE("BGN","Banglore",true,13.068777,77.717285);
    alZipList.add(objZipBE);
    objZipBE = new ClsZipBE("MDU","Madurai",true,9.968851,78.288574);
    alZipList.add(objZipBE);
    objZipBE = new ClsZipBE("KRL","Kerala",true,9.318990,77.233887);
    alZipList.add(objZipBE);
    objZipBE = new ClsZipBE("HDB","Puducherry",true,11.888853,79.705811);
    alZipList.add(objZipBE);
    objZipBE = new ClsZipBE("CBT","Coimbatore",true,11.113727,77.047119);
    alZipList.add(objZipBE);
    objZipBE = new ClsZipBE("OTY","Ooty",true,11.404649,76.690063);
    alZipList.add(objZipBE);
    objZipBE = new ClsZipBE("BGM","Belgaum",true,15.792254,74.520264);
    alZipList.add(objZipBE);
    
    
    //create an ArrayAdaptar from the array list
    objZipListAdapter = new ClsZipListAdapter(getActivity(),
      R.layout.list_row, alZipList);
    //ListView listView = (ListView) findViewById(R.id.listView1);
    // Assign adapter to ListView
    lstViewOfZip.setAdapter(objZipListAdapter);
    
    Button myButton = (Button)view. findViewById(R.id.button1);
    myButton.setOnClickListener(new OnClickListener() {
   
     @Override
     public void onClick(View v) {
   
      StringBuffer responseText = new StringBuffer();
      responseText.append("The following were selected...\n");
   
      ArrayList<ClsZipBE> alZipList = objZipListAdapter.alZipList;
      for(int i=0;i<alZipList.size();i++){
       ClsZipBE country = alZipList.get(i);
       if(country.isSelected()){
        responseText.append("\n" + country.getName());
       }
      }
      
      // show the page 2 of view pager
     ClsLauncher.objViewPager.setCurrentItem(1,true);
   
      Toast.makeText(getActivity(),
        responseText, Toast.LENGTH_LONG).show();
      
   
     }
    });
   
    
    return view;
}




}