package fr.norsys.android.norsoid.sample.adapter;

import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

import fr.norsys.android.norsoid.sample.model.Contact;

/**
 * Created by atoumji on 07/11/16.
 */
public class ContactFilter extends Filter {

    ContactAdapter contactAdapter;

    List<Contact> listContactOrigin;
    List<Contact> listContactFiltered;

    public ContactFilter(ContactAdapter contactAdapter, List<Contact> listContact) {

        super();
        this.contactAdapter = contactAdapter;
        this.listContactOrigin = listContact;
        this.listContactFiltered = new ArrayList<>();

    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        listContactFiltered.clear();
        final FilterResults results = new FilterResults();

        if (charSequence.length() == 0) {
            listContactFiltered.addAll(listContactOrigin);
        } else {
            final String filterPattern = charSequence.toString().toLowerCase().trim();

            for (final Contact contact : listContactOrigin) {
                if (contact.getName().toLowerCase().contains(filterPattern.toLowerCase())
                        || contact.getNumberPhone().toLowerCase().contains(filterPattern.toLowerCase()) ) {
                    listContactFiltered.add(contact);
                }
            }
        }
        results.values = listContactFiltered;
        results.count = listContactFiltered.size();
        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

        contactAdapter.listContact.clear();
        contactAdapter.listContact.addAll((ArrayList<Contact>) filterResults.values);
        contactAdapter.notifyDataSetChanged();

    }
}
