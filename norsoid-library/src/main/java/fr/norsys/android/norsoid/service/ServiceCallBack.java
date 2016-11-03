package fr.norsys.android.norsoid.service;

/**
 * Created by mehdibrahmi on 01/11/2016.
 */

public interface ServiceCallBack<T> {

    void onSuccess(T result);

    void onError(Throwable t);
}
