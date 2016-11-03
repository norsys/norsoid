package fr.norsys.android.norsoid.sample.config;

import fr.norsys.android.norsoid.config.NorsoidApplication;
import fr.norsys.android.norsoid.config.NorsoidComponent;
import fr.norsys.android.norsoid.config.NorsoidModule;

/**
 * Created by mehdibrahmi on 01/11/2016.
 */

public class SampleApplication extends NorsoidApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    protected NorsoidComponent getNorsoidComponent() {
        return DaggerSampleComponent.builder().norsoidModule(new NorsoidModule(this)).build();
    }
}
