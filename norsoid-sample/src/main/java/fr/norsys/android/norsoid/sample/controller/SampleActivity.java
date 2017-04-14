package fr.norsys.android.norsoid.sample.controller;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import fr.norsys.android.norsoid.controller.NorsoidActivity;
import fr.norsys.android.norsoid.manager.SharedPreferencesManager;
import fr.norsys.android.norsoid.sample.R;
import fr.norsys.android.norsoid.sample.manager.PostManager;
import fr.norsys.android.norsoid.sample.model.Post;
import fr.norsys.android.norsoid.sample.model.User;
import fr.norsys.android.norsoid.service.ServiceCallBack;
import io.realm.Realm;

public class SampleActivity extends NorsoidActivity {

    @Inject
    Context mContext;

    @Inject
    SharedPreferencesManager mSharedPreferencesManager;

    @Inject
    PostManager mPostManager;

    @BindView(R.id.username)
    EditText mEtUserName;

    @BindView(R.id.password)
    EditText mEtPassword;

    @Inject
    Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        mSharedPreferencesManager.putString("sampleKeyExist", "retrieve sampleKeyExist success");
        Toast.makeText(mContext, mSharedPreferencesManager.getString("sampleKeyExist", "sharedPreferences error"), Toast.LENGTH_SHORT).show();
        Toast.makeText(mContext, mSharedPreferencesManager.getString("sampleKeyNotExist", "retrieve sampleKeyNotExist error"), Toast.LENGTH_SHORT).show();

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

        mRealm.executeTransaction(realm -> {
            User user = realm.where(User.class).findFirst();
            if(user != null) {
                mEtUserName.setText(user.getUsername());
                mEtPassword.setText(user.getPassword());
            }
        });

    }

    @OnClick(R.id.saveUsername)
    public void onSaveUsernameClick() {
        final User user = new User(mEtUserName.getText().toString(), mEtPassword.getText().toString());
        mRealm.executeTransaction(realm -> {
            realm.delete(User.class);
            realm.copyToRealm(user);
        });

    }

}
