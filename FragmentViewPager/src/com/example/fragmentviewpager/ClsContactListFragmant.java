package com.example.fragmentviewpager;

import java.util.ArrayList;

import com.example.BE.ClsContactsBE;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class ClsContactListFragmant extends Fragment {

	public static ClsContactListFragmant newInstance(String text) {
		ClsContactListFragmant pageFragment = new ClsContactListFragmant();

		return pageFragment;
	}

	ClsContactListAdapter objContactListAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.page4_contact_list, container,
				false);
		ListView lstOfContacts = (ListView) view.findViewById(R.id.lst_contact);
		ArrayList<ClsContactsBE> alOfContacts = new ArrayList<ClsContactsBE>();
		objContactListAdapter = new ClsContactListAdapter(getActivity(),
				alOfContacts);
		lstOfContacts.setAdapter(objContactListAdapter);

		return view;
	}

	@Override
	public void setMenuVisibility(boolean menuVisible) {
		// TODO Auto-generated method stub
		super.setMenuVisibility(menuVisible);
		if (menuVisible) {

			try {
				if (objContactListAdapter.alOfContacts.size() < 1) {
					new GetContactsTask().execute(getActivity()
							.getContentResolver());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	/*
	 * Asyncc task that loads the contacts
	 */
	class GetContactsTask extends
			AsyncTask<ContentResolver, Void, ArrayList<ClsContactsBE>> {
		ProgressDialog progressDialogForLoading;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			progressDialogForLoading = new ProgressDialog(getActivity());
			progressDialogForLoading.setMessage("Loading...");
			progressDialogForLoading.setCancelable(false);

			progressDialogForLoading.show();

		}

		@Override
		protected ArrayList<ClsContactsBE> doInBackground(
				ContentResolver... params) {
			// TODO Auto-generated method stub

			ArrayList<ClsContactsBE> alOfContacts = new ArrayList<ClsContactsBE>();
			ContentResolver objContentResolver = params[0];
			Cursor curContactName = objContentResolver.query(
					ContactsContract.Contacts.CONTENT_URI, null, null, null,
					null);
			String strDisplayName = "", strEmailAddress = "", strPhoneNumber = "", strAddress = "";
			while (curContactName.moveToNext()) {
				ClsContactsBE objContact = new ClsContactsBE();
				strDisplayName = "";
				strEmailAddress = "";
				strPhoneNumber = "";
				strAddress = "";
				strDisplayName = curContactName
						.getString(curContactName
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
				String strContactId = curContactName.getString(curContactName
						.getColumnIndex(ContactsContract.Contacts._ID));
				// for getting the email id of contact
				Cursor curEmail = objContentResolver.query(Email.CONTENT_URI,
						null, Email.CONTACT_ID + " = " + strContactId, null,
						null);
				while (curEmail.moveToNext()) {
					strEmailAddress = curEmail.getString(curEmail
							.getColumnIndex(Email.DATA));
					break;
				}
				curEmail.close();

				// check wheater the person have Contact number
				if (Integer
						.parseInt(curContactName.getString(curContactName
								.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
					Cursor pCur = objContentResolver.query(
							ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
							null,
							ContactsContract.CommonDataKinds.Phone.CONTACT_ID
									+ " = ?", new String[] { strContactId },
							null);
					while (pCur.moveToNext()) {
						strPhoneNumber = pCur
								.getString(pCur
										.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
						break;
					}
					pCur.close();
				}

				// retreving the address of person
				Cursor curAddress = objContentResolver
						.query(ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI,
								null,
								ContactsContract.CommonDataKinds.StructuredPostal.CONTACT_ID
										+ " = ?",
								new String[] { strContactId }, null);
				while (curAddress.moveToNext()) {
					String street = curAddress
							.getString(curAddress
									.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
				
					String city = curAddress
							.getString(curAddress
									.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
					
					strAddress = street + "," + city;
					break;
				}
				curAddress.close();

				objContact.setStrEmailID(strEmailAddress);
				objContact.setStrContactName(strDisplayName);
				objContact.setStrPhoneNumber(strPhoneNumber);
				objContact.setStrAddress(strAddress);
				alOfContacts.add(objContact);

			}
			return alOfContacts;
		}

		@Override
		protected void onPostExecute(ArrayList<ClsContactsBE> result) {
			// TODO Auto-generated method stub

			if (progressDialogForLoading.isShowing())

			{
				progressDialogForLoading.dismiss();

			}
			objContactListAdapter.alOfContacts = result;

			objContactListAdapter.notifyDataSetChanged();

			super.onPostExecute(result);
		}

	}

}
