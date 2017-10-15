package com.murat.mocksepeti;

import android.app.Application;

import com.murat.mocksepeti.dagger.AppComponent;
import com.murat.mocksepeti.dagger.AppModule;
import com.murat.mocksepeti.dagger.DaggerAppComponent;

public class MockSepetiApplication extends Application {
    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    protected AppComponent initDagger(MockSepetiApplication application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = initDagger(this);
    }
}
