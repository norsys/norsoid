package fr.norsys.android.norsoid.util;

import android.support.annotation.NonNull;

import io.realm.Realm;

/**
 * Created by atoumji on 03/11/16.
 */

public class RealmUtil {

    public static void realmTransaction(@NonNull final Transaction transaction) {
        if(transaction == null ) {
            throw new IllegalArgumentException("transaction cannot be null.");
        }

        Realm.getDefaultInstance().executeTransaction(transaction::execute);
    }


    public interface Transaction {
        void execute(Realm realm);
    }
}
