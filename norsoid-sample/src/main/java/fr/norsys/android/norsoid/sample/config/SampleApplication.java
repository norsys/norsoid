package fr.norsys.android.norsoid.sample.config;

import com.crashlytics.android.Crashlytics;

import fr.norsys.android.norsoid.config.NorsoidApplication;
import fr.norsys.android.norsoid.config.NorsoidComponent;
import fr.norsys.android.norsoid.config.NorsoidModule;
import io.fabric.sdk.android.Fabric;

/**
 * Created by mehdibrahmi on 01/11/2016.
 */

public class SampleApplication extends NorsoidApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
    }


    @Override
    protected NorsoidComponent getNorsoidComponent() {
        return DaggerSampleComponent.builder().norsoidModule(new NorsoidModule(this)).build();
    }
}
