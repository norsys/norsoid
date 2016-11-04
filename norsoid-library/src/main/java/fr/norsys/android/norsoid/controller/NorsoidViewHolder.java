package fr.norsys.android.norsoid.controller;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;
import fr.norsys.android.norsoid.config.NorsoidApplication;

public class NorsoidViewHolder extends RecyclerView.ViewHolder {

    public NorsoidViewHolder(View itemView) {
        super(itemView);
        NorsoidApplication.app().inject(this);
        ButterKnife.bind(this, itemView);
    }
}
