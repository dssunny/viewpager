package com.example.fragmentviewpager;

import java.util.ArrayList;

import com.example.BE.ZipBE;

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

public class Page1Fragment extends Fragment {


static ZipListAdapter objZipListAdapter;
public static Page1Fragment newInstance(String text) {
    Page1Fragment pageFragment = new Page1Fragment();
   
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
    ArrayList<ZipBE> alZipList = new ArrayList<ZipBE>();
    ZipBE objZipBE = new ZipBE("AFG","Afghanistan",false,29.027167,76.466904);
    alZipList.add(objZipBE);
    objZipBE = new ZipBE("ALB","Albania",true,29.255065,76.542435);
    alZipList.add(objZipBE);
    objZipBE = new ZipBE("DZA","Algeria",false,29.273035,76.899490);
    alZipList.add(objZipBE);
    objZipBE = new ZipBE("ASM","American Samoa",true,29.223909,77.032700);
    alZipList.add(objZipBE);
    objZipBE = new ZipBE("AND","Andorra",true,29.161568,76.904984);
    alZipList.add(objZipBE);
    objZipBE = new ZipBE("AGO","Angola",false,28.803579,77.091751);
    alZipList.add(objZipBE);
    objZipBE = new ZipBE("AIA","Anguilla",false,28.657868,77.241440);
    alZipList.add(objZipBE);
   
    //create an ArrayAdaptar from the String Array
    objZipListAdapter = new ZipListAdapter(getActivity(),
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
   
      ArrayList<ZipBE> alZipList = objZipListAdapter.alZipList;
      for(int i=0;i<alZipList.size();i++){
       ZipBE country = alZipList.get(i);
       if(country.isSelected()){
        responseText.append("\n" + country.getName());
       }
      }
      
     MainActivity.objViewPager.setCurrentItem(1,true);
   
      Toast.makeText(getActivity(),
        responseText, Toast.LENGTH_LONG).show();
      
   
     }
    });
   
    
    return view;
}




}