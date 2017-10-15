package com.murat.mocksepeti.dagger;

import android.app.Application;
import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    public DatabaseReference provideDatabase() {
        return FirebaseDatabase
                .getInstance()
                .getReference("userDetails");
    }
}
