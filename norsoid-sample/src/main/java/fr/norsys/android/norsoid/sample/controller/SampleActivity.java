package fr.norsys.android.norsoid.sample.controller;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import fr.norsys.android.norsoid.controller.NorsoidActivity;
import fr.norsys.android.norsoid.manager.SharedPreferencesManager;
import fr.norsys.android.norsoid.sample.R;
import fr.norsys.android.norsoid.sample.config.SampleApplication;
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

    private Unbinder unbinder;

    @Inject
    Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SampleApplication.app().inject(this);
        setContentView(R.layout.activity_sample);
        mRealm = Realm.getDefaultInstance();
        unbinder = ButterKnife.bind(this);
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


        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.where(User.class).findFirst();
                if(user != null) {
                    mEtUserName.setText(user.getUsername());
                    mEtPassword.setText(user.getPassword());
                }
            }
        });


    }


    @OnClick(R.id.saveUsername)
    public void onSaveUsernameClick() {
        User user = new User(mEtUserName.getText().toString(), mEtPassword.getText().toString());
        mRealm.beginTransaction();
        mRealm.delete(User.class);
        mRealm.copyToRealm(user);
        mRealm.commitTransaction();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
