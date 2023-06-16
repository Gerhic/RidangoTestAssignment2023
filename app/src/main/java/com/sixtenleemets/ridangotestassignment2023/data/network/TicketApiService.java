package com.sixtenleemets.ridangotestassignment2023.data.network;

import com.sixtenleemets.ridangotestassignment2023.Ticket;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TicketApiService {

    @POST("tickets")
    @Headers("Content-Type: application/protobuf")
    Call<Void> sendTicket(@Body Ticket ticket);

}