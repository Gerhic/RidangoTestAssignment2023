package com.sixtenleemets.ridangotestassignment2023.di;

import android.content.Context;

import androidx.room.Room;

import com.sixtenleemets.ridangotestassignment2023.data.database.TicketDao;
import com.sixtenleemets.ridangotestassignment2023.data.database.TicketDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    public TicketDatabase provideTicketDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                        TicketDatabase.class, "ticket-database")
                .build();
    }

    @Provides
    @Singleton
    public TicketDao provideTicketDao(TicketDatabase database) {
        return database.ticketDao();
    }

}