package fr.norsys.android.norsoid.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import fr.norsys.android.norsoid.config.NorsoidApplication;

public class NorsoidActivity extends AppCompatActivity {

    @Inject
    Context mContext;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NorsoidApplication.app().inject(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        bind(this);
    }

    private void bind(Activity activity) {
        mUnbinder = ButterKnife.bind(activity);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        bind(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    public Context getContext() {
        return mContext;
    }
}
