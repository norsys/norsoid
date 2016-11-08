package fr.norsys.android.norsoid.sample.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.norsys.android.norsoid.controller.NorsoidViewHolder;
import fr.norsys.android.norsoid.sample.R;
import fr.norsys.android.norsoid.sample.controller.DetailContactActivity;
import fr.norsys.android.norsoid.sample.model.Contact;

/**
 * Created by atoumji on 04/11/16.
 */

public class ContactHolder extends NorsoidViewHolder {

    @BindView(R.id.nameContact)
    TextView nameTextView;
    @BindView(R.id.numberPhoneContact)
    TextView numberTextView;

    private Contact contact;


    public ContactHolder(View itemView) {
        super(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), DetailContactActivity.class);
                intent.putExtra("CONTACT", getContact());

                v.getContext().startActivity(intent);

            }
        });
    }

    public void bind(Contact contact){
        nameTextView.setText(contact.getName());
        numberTextView.setText(contact.getNumberPhone());
        this.contact = contact;
    }


    public Contact getContact() {
        return contact;
    }

}
