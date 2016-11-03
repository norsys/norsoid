package fr.norsys.android.norsoid.config;


import android.app.Application;
import android.content.Context;

import dagger.Component;
import fr.norsys.android.norsoid.manager.SharedPreferencesManager;
import io.realm.Realm;

@Component(modules = { NorsoidModule.class })
public interface NorsoidComponent {

    Context context();
    Application application();
    Realm realm();

    SharedPreferencesManager sharedPreferencesManager();

}
