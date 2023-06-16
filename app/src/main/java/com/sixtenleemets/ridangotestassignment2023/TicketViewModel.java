package com.sixtenleemets.ridangotestassignment2023;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sixtenleemets.ridangotestassignment2023.data.model.TicketEntity;
import com.sixtenleemets.ridangotestassignment2023.data.repository.TicketRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class TicketViewModel extends AndroidViewModel {

    @Inject
    TicketRepository ticketRepository;

    @Inject
    public TicketViewModel(Application application, TicketRepository ticketRepository) {
        super(application);
        this.ticketRepository = ticketRepository;
    }

    public LiveData<List<TicketEntity>> getTicketsLiveData() {
        return ticketRepository.getAllTickets();
    }

    public void addTicket(TicketEntity ticket) {
        ticketRepository.addTicket(ticket);
    }

}