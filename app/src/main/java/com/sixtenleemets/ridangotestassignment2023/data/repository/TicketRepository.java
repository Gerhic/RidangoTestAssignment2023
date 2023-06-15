package com.sixtenleemets.ridangotestassignment2023.data.repository;

import androidx.lifecycle.LiveData;

import com.sixtenleemets.ridangotestassignment2023.data.database.TicketDao;
import com.sixtenleemets.ridangotestassignment2023.data.model.Ticket;

import java.util.List;

import javax.inject.Inject;

public class TicketRepository {
    private final TicketDao ticketDao;

    @Inject
    public TicketRepository(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public LiveData<List<Ticket>> getAllTickets() {
        return ticketDao.getAllTickets();
    }

    public void addTicket(Ticket ticket) {
        ticketDao.insert(ticket);
    }

}