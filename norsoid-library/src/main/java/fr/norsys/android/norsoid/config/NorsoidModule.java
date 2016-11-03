package fr.norsys.android.norsoid.config;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import dagger.Module;
import dagger.Provides;
import fr.norsys.android.norsoid.manager.SharedPreferencesManager;
import fr.norsys.android.norsoid.manager.impl.SharedPreferencesManagerImpl;
import io.realm.Realm;

@Module
public class NorsoidModule {

    private final Application mApplication;

    public NorsoidModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    public Application provideApplication() {
        return this.mApplication;
    }

    @Provides
    public Context provideContext() {
        return this.mApplication.getApplicationContext();
    }

    @Provides
    public Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

    @Provides
    public SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    public SharedPreferencesManager provideSharedPreferencesManager(SharedPreferences sharedPreferences) {
        return new SharedPreferencesManagerImpl(sharedPreferences);
    }

}
