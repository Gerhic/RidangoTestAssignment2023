package com.sixtenleemets.ridangotestassignment2023.util;

import com.sixtenleemets.ridangotestassignment2023.Ticket;
import com.sixtenleemets.ridangotestassignment2023.data.model.TicketEntity;

public class TicketConverter {

    public static Ticket convertToTicket(TicketEntity ticketEntity) {
        Ticket.Builder builder = Ticket.newBuilder();
        builder.setId(ticketEntity.getId());
        builder.setProductName(ticketEntity.getProductName());
        builder.setPrice(ticketEntity.getPriceCents());
        return builder.build();
    }

    public static TicketEntity convertToTicketEntity(Ticket ticket) {
        TicketEntity entity = new TicketEntity(ticket.getProductName(), ticket.getPrice());
        entity.setId(ticket.getId());
        return entity;
    }

}