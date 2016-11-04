package fr.norsys.android.norsoid.sample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.norsys.android.norsoid.controller.NorsoidViewHolder;
import fr.norsys.android.norsoid.sample.R;
import fr.norsys.android.norsoid.sample.model.Contact;

/**
 * Created by atoumji on 04/11/16.
 */

public class ContactHolder extends NorsoidViewHolder {

    @BindView(R.id.nameContact)
    TextView nameTextView;
    @BindView(R.id.numberPhoneContact)
    TextView numberTextView;


    public ContactHolder(View itemView) {
        super(itemView);
    }

    public void bind(Contact contact){
        nameTextView.setText(contact.getName());
        numberTextView.setText(contact.getNumberPhone());
    }
}
