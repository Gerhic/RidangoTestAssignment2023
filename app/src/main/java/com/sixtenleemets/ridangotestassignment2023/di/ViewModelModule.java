package com.sixtenleemets.ridangotestassignment2023.di;

import android.app.Application;

import com.sixtenleemets.ridangotestassignment2023.TicketViewModel;
import com.sixtenleemets.ridangotestassignment2023.data.repository.TicketRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class ViewModelModule {

    @Provides
    @Singleton
    TicketViewModel provideTicketViewModel(Application application, TicketRepository ticketRepository) {
        return new TicketViewModel(application, ticketRepository);
    }

}
