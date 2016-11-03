package fr.norsys.android.norsoid.controller;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import fr.norsys.android.norsoid.config.NorsoidApplication;

public class NorsoidActivity extends AppCompatActivity {

    @Inject
    Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NorsoidApplication.app().inject(this);
    }


    public Context getContext() {
        return mContext;
    }
}
