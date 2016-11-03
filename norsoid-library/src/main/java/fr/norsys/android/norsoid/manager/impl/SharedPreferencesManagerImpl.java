package fr.norsys.android.norsoid.manager.impl;

import android.content.SharedPreferences;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.util.Set;

import fr.norsys.android.norsoid.manager.SharedPreferencesManager;

public class SharedPreferencesManagerImpl implements SharedPreferencesManager {

    private final SharedPreferences mSharedPreferences;

    public SharedPreferencesManagerImpl(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    @Override
    public String getString(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    @Override
    public Set<String> getStringSet(String key, Set<String> defaultValue) {
        return mSharedPreferences.getStringSet(key, defaultValue);
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return mSharedPreferences.getInt(key, defaultValue);
    }

    @Override
    public long getLong(String key, long defaultValue) {
        return mSharedPreferences.getLong(key, defaultValue);
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        return mSharedPreferences.getFloat(key, defaultValue);
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    @Override
    public <T> T getObject(String key, T defaultValue, Class<T> clazz) {
        String json = mSharedPreferences.getString(key, null);
        try {
            return new GsonBuilder().create().fromJson(json, clazz);
        } catch (JsonSyntaxException e) {
            return defaultValue;
        }
    }

    @Override
    public void putString(String key, String value) {
        mSharedPreferences.edit().putString(key, value).apply();
    }

    @Override
    public void putStringSet(String key, Set<String> value) {
        mSharedPreferences.edit().putStringSet(key, value).apply();
    }

    @Override
    public void putInt(String key, int value) {
        mSharedPreferences.edit().putInt(key, value).apply();
    }

    @Override
    public void putLong(String key, long value) {
        mSharedPreferences.edit().putLong(key, value).apply();
    }

    @Override
    public void putFloat(String key, float value) {
        mSharedPreferences.edit().putFloat(key, value).apply();
    }

    @Override
    public void putBoolean(String key, boolean value) {
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    @Override
    public <T> void putObject(String key, T value) {
        putString(key, new GsonBuilder().create().toJson(value));
    }

    @Override
    public void remove(String key) {
        mSharedPreferences.edit().remove(key).apply();
    }

    @Override
    public void clear() {
        mSharedPreferences.edit().clear().apply();
    }
}
