package fr.norsys.android.norsoid.manager;

import java.util.Set;

/**
 * Created by mehdibrahmi on 31/10/2016.
 */

public interface SharedPreferencesManager {

    String getString(String key, String defaultValue);

    Set<String> getStringSet(String key, Set<String> defaultValue);

    int getInt(String key, int defaultValue);

    long getLong(String key, long defaultValue);

    float getFloat(String key, float defaultValue);

    boolean getBoolean(String key, boolean defaultValue);

    <T> T getObject(String key, T defaultValue, Class<T> clazz);

    void putString(String key, String value);

    void putStringSet(String key, Set<String> value);

    void putInt(String key, int value);

    void putLong(String key, long value);

    void putFloat(String key, float value);

    void putBoolean(String key, boolean value);

    <T> void putObject(String key, T value);

    void remove(String key);

    void clear();
}
