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

    public static NorsoidApplication app() {
        return sApplication;
    }

    protected NorsoidComponent getNorsoidComponent() {
        if(this.mNorsoidComponent == null) {
            this.mNorsoidComponent = this.createNorsoidComponent();
        }
        return this.mNorsoidComponent;
    }

    protected NorsoidComponent createNorsoidComponent() {
        return DaggerNorsoidComponent.builder().norsoidModule(new NorsoidModule(this)).build();
    }

    public void inject(Object obj) {
        if (obj != null) {
            this.invokeInject(getNorsoidComponent(), obj, obj.getClass());
        }
    }

    private void invokeInject(NorsoidComponent baseComponent, Object obj, Class objClass) {
        try {
            Method e = baseComponent.getClass().getMethod("inject", new Class[]{objClass});
            e.invoke(baseComponent, new Object[]{obj});
        } catch (NoSuchMethodException var5) {
            if (objClass.getSuperclass() == null) {
                throw new IllegalStateException("inject(" + obj.getClass().getSimpleName() + ") is not declared in your BaseComponent", var5);
            }

            this.invokeInject(baseComponent, obj, objClass.getSuperclass());
        } catch (Exception var6) {
            throw new RuntimeException("Can\'t inject in " + obj.getClass() + "  with your BaseComponent ", var6);
        }
    }
}
