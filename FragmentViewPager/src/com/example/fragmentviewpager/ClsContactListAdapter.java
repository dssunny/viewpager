package com.example.fragmentviewpager;

import java.util.ArrayList;

import com.example.BE.ClsContactsBE;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ClsContactListAdapter extends BaseAdapter {

	public ArrayList<ClsContactsBE> alOfContacts = null;
	private boolean useList = true;
	private Context context = null;

	ClsContactListAdapter(Context context, ArrayList<ClsContactsBE> alOfContacts) {
		this.alOfContacts = alOfContacts;
		this.context = context;
	}

	private class ViewHolder {
		TextView txtName;
		TextView txtPhone;
		TextView txtEmail;
		TextView txtAddress;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return alOfContacts.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;

		ClsContactsBE objContact = alOfContacts.get(position);
		View viewToUse = null; // we want to support a grid or list view.

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			if (useList) {
				viewToUse = mInflater.inflate(R.layout.contact_list_row, null);
			} else {
				viewToUse = mInflater.inflate(R.layout.contact_list_row, null);
			}

			holder = new ViewHolder();
			holder.txtName = (TextView) viewToUse.findViewById(R.id.tvName);
			holder.txtAddress = (TextView) viewToUse
					.findViewById(R.id.tvAddress);
			holder.txtEmail = (TextView) viewToUse.findViewById(R.id.tvEmailID);
			holder.txtPhone = (TextView) viewToUse.findViewById(R.id.tvPhone);

			viewToUse.setTag(holder);
		} else {
			viewToUse = convertView;
			holder = (ViewHolder) viewToUse.getTag();
		}
		holder.txtName.setText(objContact.getStrContactName());
		holder.txtAddress.setText(objContact.getStrAddress());
		holder.txtEmail.setText(objContact.getStrEmailID());
		holder.txtPhone.setText(objContact.getStrPhoneNumber());
		return viewToUse;

	}

}
