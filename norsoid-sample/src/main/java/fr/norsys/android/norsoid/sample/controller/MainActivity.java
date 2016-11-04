package fr.norsys.android.norsoid.sample.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.OnClick;
import fr.norsys.android.norsoid.controller.NorsoidActivity;
import fr.norsys.android.norsoid.sample.R;

public class MainActivity extends NorsoidActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @OnClick({R.id.btnSampleActivity, R.id.btnSampleTabActivity})
    public void onBtnClick(View v) {
        switch (v.getId()) {
            case R.id.btnSampleActivity:
                startActivity(new Intent(this, SampleActivity.class));
                break;
            case R.id.btnSampleTabActivity:
                startActivity(new Intent(this, SampleTabActivity.class));
                break;
        }
    }
}
