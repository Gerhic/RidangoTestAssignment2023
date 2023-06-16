package com.sixtenleemets.ridangotestassignment2023.data.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.sixtenleemets.ridangotestassignment2023.data.model.TicketEntity;

import java.util.List;

@Dao
public interface TicketDao {
    @Insert
    void insert(TicketEntity ticket);

    @Query("SELECT * FROM tickets")
    LiveData<List<TicketEntity>> getAllTickets();
}