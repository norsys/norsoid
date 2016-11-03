package fr.norsys.android.norsoid.sample.controller;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import fr.norsys.android.norsoid.controller.NorsoidActivity;
import fr.norsys.android.norsoid.manager.SharedPreferencesManager;
import fr.norsys.android.norsoid.sample.R;
import fr.norsys.android.norsoid.sample.config.SampleApplication;
import fr.norsys.android.norsoid.sample.manager.PostManager;
import fr.norsys.android.norsoid.sample.post.Post;
import fr.norsys.android.norsoid.service.ServiceCallBack;

public class SampleActivity extends NorsoidActivity {

    @Inject
    Context mContext;

    @Inject
    SharedPreferencesManager mSharedPreferencesManager;

    @Inject
    PostManager mPostManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SampleApplication.app().inject(this);
        setContentView(R.layout.activity_sample);
        mSharedPreferencesManager.putString("sampleKey", "Hello World !");
        Toast.makeText(mContext, mSharedPreferencesManager.getString("sampleKey", "sharedPreferences error"), Toast.LENGTH_SHORT).show();
        Toast.makeText(mContext, mSharedPreferencesManager.getString("sampleKeyError", "sharedPreferences error"), Toast.LENGTH_SHORT).show();

        mPostManager.getPost("1", new ServiceCallBack<Post>() {
            @Override
            public void onSuccess(Post post) {
                mSharedPreferencesManager.putObject("post", post);
                Toast.makeText(mContext, mSharedPreferencesManager.getObject("post", null, Post.class).getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable throwable) {
                Toast.makeText(mContext, "service error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
