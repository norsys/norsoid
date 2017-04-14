package fr.norsys.android.norsoid.config;

import android.app.Application;

import java.lang.reflect.Method;

import io.realm.Realm;

public class NorsoidApplication extends Application {

    protected NorsoidComponent mNorsoidComponent;

    protected static NorsoidApplication sApplication;

    public NorsoidApplication() {}

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        // Initialize Realm. Should only be done once when the application starts.
        Realm.init(this);
    }

    protected static NorsoidApplication app() {
        return sApplication;
    }

    protected NorsoidComponent getNorsoidComponent() {
        if(this.mNorsoidComponent == null) {
            this.mNorsoidComponent = this.createNorsoidComponent();
        }
        return this.mNorsoidComponent;
    }

    private NorsoidComponent createNorsoidComponent() {
        return DaggerNorsoidComponent.builder().norsoidModule(new NorsoidModule(this)).build();
    }

    protected void inject(Object obj) {
        if (obj != null) {
            inject(getNorsoidComponent(), obj, obj.getClass());
        }
    }

    private void inject(NorsoidComponent baseComponent, Object obj, Class objClass) {
        try {
            Method e = baseComponent.getClass().getMethod("inject", objClass);
            e.invoke(baseComponent, obj);
        } catch (NoSuchMethodException e) {
            if (objClass.getSuperclass() == null) {
                throw new IllegalStateException("inject(" + obj.getClass().getSimpleName() + ") is not declared in your Component", e);
            }
            inject(baseComponent, obj, objClass.getSuperclass());
        } catch (Exception e) {
            throw new RuntimeException("Can\'t inject in " + obj.getClass() + "  with your Component ", e);
        }
    }
}
