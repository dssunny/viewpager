package com.example.fragmentviewpager;

import java.util.ArrayList;

import com.example.BE.ContactsBE;

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

public class Page4Fragment extends Fragment {

	public static Page4Fragment newInstance(String text) {
		Page4Fragment pageFragment = new Page4Fragment();

		return pageFragment;
	}

	ContactListAdapter objContactListAdapter;

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
		ArrayList<ContactsBE> alOfContacts = new ArrayList<ContactsBE>();
		objContactListAdapter = new ContactListAdapter(getActivity(),
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
			AsyncTask<ContentResolver, Void, ArrayList<ContactsBE>> {
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
		protected ArrayList<ContactsBE> doInBackground(
				ContentResolver... params) {
			// TODO Auto-generated method stub

			ArrayList<ContactsBE> alOfContacts = new ArrayList<ContactsBE>();
			ContentResolver objContentResolver = params[0];
			Cursor cursor = objContentResolver.query(ContactsContract.Contacts.CONTENT_URI,
					null, null, null, null);
			String strDisplayName = "", strEmailAddress = "", strPhoneNumber = "", strAddress = "";
			while (cursor.moveToNext()) {
				ContactsBE objContact = new ContactsBE();
				strDisplayName = "";
				strEmailAddress = "";
				strPhoneNumber = "";
				strAddress = "";
				strDisplayName = cursor
						.getString(cursor
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
				String id = cursor.getString(cursor
						.getColumnIndex(ContactsContract.Contacts._ID));
				Cursor emails = objContentResolver.query(Email.CONTENT_URI, null,
						Email.CONTACT_ID + " = " + id, null, null);
				while (emails.moveToNext()) {
					strEmailAddress = emails.getString(emails
							.getColumnIndex(Email.DATA));
					break;
				}
				emails.close();

				if (Integer
						.parseInt(cursor.getString(cursor
								.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
					Cursor pCur = objContentResolver.query(
							ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
							null,
							ContactsContract.CommonDataKinds.Phone.CONTACT_ID
									+ " = ?", new String[] { id }, null);
					while (pCur.moveToNext()) {
						strPhoneNumber = pCur
								.getString(pCur
										.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
						break;
					}
					pCur.close();
				}
				Cursor address_cursror = objContentResolver
						.query(ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI,
								null,
								ContactsContract.CommonDataKinds.StructuredPostal.CONTACT_ID
										+ " = ?", new String[] { id }, null);
				while (address_cursror.moveToNext()) {
					String street = address_cursror
							.getString(address_cursror
									.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
					System.out.println("Street ::" + street);
					String city = address_cursror
							.getString(address_cursror
									.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
					System.out.println("City ::" + city);
					strAddress = street + "," + city;
					break;
				}
				address_cursror.close();

				objContact.setStrEmailID(strEmailAddress);
				objContact.setStrContactName(strDisplayName);
				objContact.setStrPhoneNumber(strPhoneNumber);
				objContact.setStrAddress(strAddress);
				alOfContacts.add(objContact);

			}
			return alOfContacts;
		}

		@Override
		protected void onPostExecute(ArrayList<ContactsBE> result) {
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
