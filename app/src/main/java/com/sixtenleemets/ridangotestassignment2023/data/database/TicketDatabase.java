package com.sixtenleemets.ridangotestassignment2023.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.sixtenleemets.ridangotestassignment2023.data.model.TicketEntity;

@Database(entities = {TicketEntity.class}, version = 1, exportSchema = false)
public abstract class TicketDatabase extends RoomDatabase {
    public abstract TicketDao ticketDao();
}

