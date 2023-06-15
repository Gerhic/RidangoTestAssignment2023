package com.sixtenleemets.ridangotestassignment2023.di;

import android.app.Application;
import android.content.Context;

import com.sixtenleemets.ridangotestassignment2023.adapter.TicketAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public Context provideContext(Application application) {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    public TicketAdapter provideTicketAdapter() {
        return new TicketAdapter();
    }

}