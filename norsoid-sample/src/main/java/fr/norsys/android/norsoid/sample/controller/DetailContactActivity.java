package fr.norsys.android.norsoid.sample.controller;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import butterknife.BindView;
import fr.norsys.android.norsoid.controller.NorsoidActivity;
import fr.norsys.android.norsoid.sample.R;

/**
 * Created by atoumji on 07/11/16.
 */

public class DetailContactActivity extends NorsoidActivity {

    private static final int MY_PERMISSIONS_REQUEST_CALL = 2;
    @BindView(R.id.nameContact)
    TextView mNameContact;

    @BindView(R.id.numberPhoneContact)
    TextView mNumberPhoneContact;

    @BindView(R.id.buttonCall)
    ImageButton mButtonCall;

    String nameContact, numberPhoneContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contact);

        Intent intent = getIntent();
        nameContact = intent.getStringExtra("CONTACT_NAME");
        numberPhoneContact = intent.getStringExtra("CONTACT_PHONE");

        mNameContact.setText(nameContact.toString());
        mNumberPhoneContact.setText(numberPhoneContact.toString());

        mButtonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                makeCallPhone();
            }
        });


    }

    private void makeCallPhone() {
        if (ActivityCompat.checkSelfPermission(DetailContactActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(DetailContactActivity.this,
                    Manifest.permission.CALL_PHONE)) {

                Toast.makeText(DetailContactActivity.this, "shouldShowRequestPermissionRationale", Toast.LENGTH_LONG).show();

            } else {

                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(DetailContactActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_CALL);

                Toast.makeText(DetailContactActivity.this, "requestPermissions", Toast.LENGTH_LONG).show();
            }

        } else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + numberPhoneContact));
            startActivity(callIntent);
        }
    }

    @Override
   public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    makeCallPhone();
                } else {

                    Toast.makeText(this, "No enable to call", Toast.LENGTH_LONG).show();

                }
                return;
            }

        }
    }



}
