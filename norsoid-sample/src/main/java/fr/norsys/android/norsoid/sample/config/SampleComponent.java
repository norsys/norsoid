package fr.norsys.android.norsoid.sample.config;


import dagger.Component;
import fr.norsys.android.norsoid.config.NorsoidComponent;
import fr.norsys.android.norsoid.config.NorsoidModule;
import fr.norsys.android.norsoid.sample.controller.SampleActivity;
import fr.norsys.android.norsoid.sample.manager.PostManager;

@Component(modules = {
        NorsoidModule.class,
        SampleModule.class
})
public interface SampleComponent extends NorsoidComponent {

    PostManager postManager();

    void inject(SampleActivity sampleActivity);

}
