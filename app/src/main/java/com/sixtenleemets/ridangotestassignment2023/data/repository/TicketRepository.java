package com.sixtenleemets.ridangotestassignment2023.data.repository;

import com.sixtenleemets.ridangotestassignment2023.data.database.TicketDao;
import com.sixtenleemets.ridangotestassignment2023.data.model.Ticket;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TicketRepository {
    private final TicketDao ticketDao;

    @Inject
    public TicketRepository(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public Flowable<List<Ticket>> getAllTickets() {
        return ticketDao.getAllTickets().subscribeOn(Schedulers.io());
    }

    public Completable addTicket(Ticket ticket) {
        return Completable.fromAction(() -> ticketDao.insertTicket(ticket))
                .subscribeOn(Schedulers.io());
    }

}