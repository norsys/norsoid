package fr.norsys.android.norsoid.controller;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;
import fr.norsys.android.norsoid.config.Norsoid;

public class NorsoidViewHolder extends RecyclerView.ViewHolder {

    public NorsoidViewHolder(View itemView) {
        super(itemView);
        Norsoid.inject(this);
        ButterKnife.bind(this, itemView);
    }
}
