package fr.norsys.android.norsoid.config;

/**
 * Created by mehdibrahmi on 14/04/2017.
 */

public class Norsoid {

    /**
     * Dagger injection into value object.
     *
     * @param value
     */
    public static void inject(Object value) {
        NorsoidApplication.app().inject(value);
    }
}
