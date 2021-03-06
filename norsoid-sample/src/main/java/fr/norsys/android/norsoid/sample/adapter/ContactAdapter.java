package fr.norsys.android.norsoid.sample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.List;

import fr.norsys.android.norsoid.sample.R;
import fr.norsys.android.norsoid.sample.model.Contact;

/**
 * Created by atoumji on 04/11/16.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactHolder> implements Filterable {

    List<Contact> listContact, listContactFiltered;
    ContactFilter contactFilter;


    public ContactAdapter(List<Contact> listContact){
        this.listContact = listContact;
        this.listContactFiltered = listContact;
        this.contactFilter = new ContactFilter(this, listContact);
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_contact,parent,false);
        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactHolder contactHolder, int position) {
        Contact contact = listContactFiltered.get(position);
        contactHolder.bind(contact);
    }

    @Override
    public int getItemCount() {
        return listContactFiltered.size();
    }


    @Override
    public Filter getFilter() {
        return contactFilter;
    }

    protected void setListContactFiltered(List<Contact> listContactFiltered) {
        this.listContactFiltered = listContactFiltered;
    }
}
