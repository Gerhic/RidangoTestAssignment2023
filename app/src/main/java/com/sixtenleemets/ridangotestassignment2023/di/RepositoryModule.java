package com.sixtenleemets.ridangotestassignment2023.di;

import com.sixtenleemets.ridangotestassignment2023.data.database.TicketDao;
import com.sixtenleemets.ridangotestassignment2023.data.network.TicketApiService;
import com.sixtenleemets.ridangotestassignment2023.data.repository.TicketRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RepositoryModule {

    @Singleton
    @Provides
    TicketRepository provideTicketRepository(TicketDao ticketDao, TicketApiService ticketApiService) {
        return new TicketRepository(ticketDao, ticketApiService);
    }
}