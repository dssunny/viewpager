package com.example.fragmentviewpager;

import java.util.ArrayList;

import com.example.BE.ZipBE;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ZipListAdapter extends ArrayAdapter<ZipBE> {
	 
	  public ArrayList<ZipBE> alZipList;
	  Context objContext;
	  public ZipListAdapter(Context context, int textViewResourceId, 
	    ArrayList<ZipBE> countryList) {
		  super(context, textViewResourceId, countryList);
	   this.objContext=context;
	   this.alZipList = new ArrayList<ZipBE>();
	   this.alZipList.addAll(countryList);
	  }
	 
	  private class ViewHolder {
	   TextView code;
	   CheckBox name;
	  }
	 
	
	  public View getView(int position, View convertView, ViewGroup parent) {
	 
	   ViewHolder holder = null;
	   Log.v("ConvertView", String.valueOf(position));
	 
	   if (convertView == null) {
	   LayoutInflater vi = (LayoutInflater)objContext.getSystemService(
	     Context.LAYOUT_INFLATER_SERVICE);
	   convertView = vi.inflate(R.layout.list_row, null);
	 
	   holder = new ViewHolder();
	   holder.code = (TextView) convertView.findViewById(R.id.code);
	   holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
	   convertView.setTag(holder);
	 
	    holder.name.setOnClickListener( new View.OnClickListener() {  
	     public void onClick(View v) {  
	      CheckBox cb = (CheckBox) v ;  
	      ZipBE country = (ZipBE) cb.getTag();  
	      
	      country.setSelected(cb.isChecked());
	     }  
	    });  
	   } 
	   else {
	    holder = (ViewHolder) convertView.getTag();
	   }
	 
	   ZipBE country = alZipList.get(position);
	   holder.code.setText(" (" +  country.getLatitude()+","+country.getLongitude() + ")");
	   holder.name.setText(country.getName());
	   holder.name.setChecked(country.isSelected());
	   holder.name.setTag(country);
	 
	   return convertView;
	 
	  }
	 
	 }