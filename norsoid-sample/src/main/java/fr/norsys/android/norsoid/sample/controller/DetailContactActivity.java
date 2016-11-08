package fr.norsys.android.norsoid.sample.controller;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import fr.norsys.android.norsoid.controller.NorsoidActivity;
import fr.norsys.android.norsoid.sample.R;
import fr.norsys.android.norsoid.sample.model.Contact;

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

    Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contact);

        Intent intent = getIntent();
        contact = intent.getParcelableExtra("CONTACT");

        mNameContact.setText(contact.getName());
        mNumberPhoneContact.setText(contact.getNumberPhone());

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
            callIntent.setData(Uri.parse("tel:" + contact.getNumberPhone()));
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
