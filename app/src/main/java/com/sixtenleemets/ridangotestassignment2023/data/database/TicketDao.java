package com.sixtenleemets.ridangotestassignment2023.data.database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.sixtenleemets.ridangotestassignment2023.data.model.Ticket;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface TicketDao {
    @Insert
    void insertTicket(Ticket ticket);

    @Query("SELECT * FROM tickets")
    Flowable<List<Ticket>> getAllTickets();
}