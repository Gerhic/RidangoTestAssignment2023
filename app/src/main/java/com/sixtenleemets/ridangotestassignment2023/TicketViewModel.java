package com.sixtenleemets.ridangotestassignment2023;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sixtenleemets.ridangotestassignment2023.data.model.Ticket;
import com.sixtenleemets.ridangotestassignment2023.data.repository.TicketRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class TicketViewModel extends AndroidViewModel {

    @Inject
    TicketRepository ticketRepository;
    private final MutableLiveData<List<Ticket>> ticketsLiveData = new MutableLiveData<>();

    @Inject
    public TicketViewModel(Application application, TicketRepository ticketRepository) {
        super(application);
        this.ticketRepository = ticketRepository;
    }

    public LiveData<List<Ticket>> getTicketsLiveData() {
        return ticketsLiveData;
    }

    public Completable addTicket(Ticket ticket) {
        return Completable.fromAction(() -> ticketRepository.addTicket(ticket))
                .subscribeOn(Schedulers.io());
    }


}