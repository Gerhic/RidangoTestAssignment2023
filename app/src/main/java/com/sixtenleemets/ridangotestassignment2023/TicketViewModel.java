package com.sixtenleemets.ridangotestassignment2023;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sixtenleemets.ridangotestassignment2023.data.model.TicketEntity;
import com.sixtenleemets.ridangotestassignment2023.data.repository.TicketRepository;
import com.sixtenleemets.ridangotestassignment2023.util.TicketConverter;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

@HiltViewModel
public class TicketViewModel extends AndroidViewModel {

    private static final String TAG = "TicketViewModel";

    @Inject
    TicketRepository ticketRepository;

    private final MutableLiveData<Boolean> ticketAddedLiveData;

    @Inject
    public TicketViewModel(Application application, TicketRepository ticketRepository) {
        super(application);
        this.ticketRepository = ticketRepository;
        ticketAddedLiveData = new MutableLiveData<>();
    }

    public LiveData<Boolean> getTicketAddedLiveData() {
        return ticketAddedLiveData;
    }

    public LiveData<List<TicketEntity>> getTicketsLiveData() {
        return ticketRepository.getAllTickets();
    }

    // TODO refactor with useCases
    public void addTicket(TicketEntity ticketEntity) {
        Completable.fromAction(() -> ticketRepository.insertTicket(ticketEntity))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        Log.d(TAG, "addTicket onSubscribe");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "addTicket onComplete");
                        sendTicket(ticketEntity);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.w(TAG, "addTicket onError: ", e);
                        ticketAddedLiveData.postValue(false);
                    }
                });
    }

    private void sendTicket(TicketEntity ticketEntity) {
        Ticket ticket = TicketConverter.convertToTicket(ticketEntity);
        ticketRepository.sendTicket(ticket).enqueue(new Callback<Void>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "sendTicket successful");
                    ticketAddedLiveData.postValue(true);
                } else {
                    Log.d(TAG, "sendTicket fail");
                    ticketAddedLiveData.postValue(false);
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<Void> call, Throwable t) {
                // FIXME currently mocking server's success response by letting this fly
                Log.d(TAG, "sendTicket onFailure (continuing as if success)");
                ticketAddedLiveData.postValue(true);
            }
        });
    }
}