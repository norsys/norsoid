package fr.norsys.android.norsoid.sample.controller;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import fr.norsys.android.norsoid.controller.NorsoidActivity;
import fr.norsys.android.norsoid.sample.R;
import fr.norsys.android.norsoid.sample.adapter.ContactAdapter;
import fr.norsys.android.norsoid.sample.model.Contact;

/**
 * Created by atoumji on 04/11/16.
 */

public class ContactActivity extends NorsoidActivity {

    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    @BindView(R.id.listContact)
    RecyclerView mListContact;

    List<Contact> listContact = new ArrayList<Contact>();
    ContactAdapter contactAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                Toast.makeText(this, "shouldShowRequestPermissionRationale", Toast.LENGTH_LONG).show();


            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                Toast.makeText(this, "requestPermissions", Toast.LENGTH_LONG).show();


                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }

        } else {
            getListContactPhone();
        }


    }

    private void getListContactPhone() {
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, ContactsContract.Contacts.DISPLAY_NAME+" ASC");
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if (Integer.parseInt(cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        //Toast.makeText(this, "Name: " + name + ", Phone No: " + phoneNo, Toast.LENGTH_SHORT).show();
                        listContact.add(new Contact(name,phoneNo));
                    }
                    pCur.close();
                }
            }
        }
         contactAdapter = new ContactAdapter(listContact);

        mListContact.setLayoutManager(new LinearLayoutManager(this));
        mListContact.setAdapter(contactAdapter);
        mListContact.setHasFixedSize(true);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    getListContactPhone();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                    Toast.makeText(this, "No enable to add/read contact", Toast.LENGTH_LONG).show();

                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                if ( TextUtils.isEmpty ( s ) ) {
                    contactAdapter.getFilter().filter("");
                } else {
                    contactAdapter.getFilter().filter(s.toString());
                }
                return true;

            }
        });

        return true;
    }


//    @OnClick(R.id.addContact)
//    public void onAddContactClick() {
//
//
//        // Here, thisActivity is the current activity
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.READ_CONTACTS)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    Manifest.permission.READ_CONTACTS)) {
//
//                // Show an expanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//                Toast.makeText(this, "shouldShowRequestPermissionRationale", Toast.LENGTH_LONG).show();
//
//
//            } else {
//
//                // No explanation needed, we can request the permission.
//
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.READ_CONTACTS},
//                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
//
//                Toast.makeText(this, "requestPermissions", Toast.LENGTH_LONG).show();
//
//
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
//        }
//    }

//        try {
//            ContentResolver cr = this.getContentResolver();
//            ContentValues cv = new ContentValues();
//            cv.put(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, mSearchText.getText().toString());
//            cv.put(ContactsContract.CommonDataKinds.Phone.NUMBER, "1234567890");
//            cv.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
//            cr.insert(ContactsContract.RawContacts.CONTENT_URI, cv);
//
//            Toast.makeText(this, "Contact added "+  mSearchText.getText().toString(), Toast.LENGTH_LONG).show();
//        } catch(Exception e) {
//
//            TextView tv = new TextView(this);
//            tv.setText(e.toString());
//            setContentView(tv);
//        }

//    }

}
