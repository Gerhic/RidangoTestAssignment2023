package com.sixtenleemets.ridangotestassignment2023.data.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.sixtenleemets.ridangotestassignment2023.data.model.Ticket;

import java.util.List;

@Dao
public interface TicketDao {
    @Insert
    void insert(Ticket ticket);

    @Query("SELECT * FROM tickets")
    LiveData<List<Ticket>> getAllTickets();
}