package com.sixtenleemets.ridangotestassignment2023.data.repository;

import androidx.lifecycle.LiveData;

import com.sixtenleemets.ridangotestassignment2023.Ticket;
import com.sixtenleemets.ridangotestassignment2023.data.database.TicketDao;
import com.sixtenleemets.ridangotestassignment2023.data.model.TicketEntity;
import com.sixtenleemets.ridangotestassignment2023.data.network.TicketApiService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class TicketRepository {
    private final TicketDao ticketDao;
    private final TicketApiService ticketApiService;

    @Inject
    public TicketRepository(TicketDao ticketDao, TicketApiService ticketApiService) {
        this.ticketDao = ticketDao;
        this.ticketApiService = ticketApiService;
    }

    public LiveData<List<TicketEntity>> getAllTickets() {
        return ticketDao.getAllTickets();
    }

    public void insertTicket(TicketEntity ticketEntity) {
        ticketDao.insert(ticketEntity);
    }

    public Call<Void> sendTicket(Ticket ticket) {
        return ticketApiService.sendTicket(ticket);
    }

}