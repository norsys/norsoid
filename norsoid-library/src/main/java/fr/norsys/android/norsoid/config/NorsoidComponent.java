package fr.norsys.android.norsoid.config;


import dagger.Component;
import fr.norsys.android.norsoid.controller.NorsoidActivity;
import fr.norsys.android.norsoid.controller.NorsoidFragment;
import fr.norsys.android.norsoid.controller.NorsoidViewHolder;

@Component(modules = { NorsoidModule.class })
public interface NorsoidComponent {

    void inject(NorsoidActivity norsoidActivity);

    void inject(NorsoidFragment norsoidFragment);

    void inject(NorsoidViewHolder norsoidViewHolder);

}
