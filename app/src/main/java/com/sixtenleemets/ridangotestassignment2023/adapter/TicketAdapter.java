package com.sixtenleemets.ridangotestassignment2023.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sixtenleemets.ridangotestassignment2023.R;
import com.sixtenleemets.ridangotestassignment2023.data.model.TicketEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import dagger.hilt.android.scopes.FragmentScoped;

@FragmentScoped
public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {

    private final List<TicketEntity> tickets = new ArrayList<>();

    public void setTickets(List<TicketEntity> newTickets) {
        TicketDiffCallback diffCallback = new TicketDiffCallback(tickets, newTickets);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        tickets.clear();
        tickets.addAll(newTickets);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item view layout and create the view holder
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket, parent, false);
        return new TicketViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        // Bind the ticket data to the view holder
        TicketEntity ticket = tickets.get(position);
        holder.bind(ticket);
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    static class TicketViewHolder extends RecyclerView.ViewHolder {
        static final String DATETIME_FORMAT = "dd MMM yyyy";
        private final TextView productNameTextView;
        private final TextView priceTextView;
        private final TextView dateTextView;

        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }

        public void bind(TicketEntity ticket) {
            // Bind the ticket data to the view elements
            productNameTextView.setText(ticket.getProductName());
            priceTextView.setText(String.valueOf(ticket.getPriceCents()));

            // Format and display the date
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATETIME_FORMAT, Locale.getDefault());
            String formattedDate = dateFormat.format(ticket.getDateMillis());
            dateTextView.setText(formattedDate);
        }
    }

    private static class TicketDiffCallback extends DiffUtil.Callback {
        private final List<TicketEntity> oldTickets;
        private final List<TicketEntity> newTickets;

        public TicketDiffCallback(List<TicketEntity> oldTickets, List<TicketEntity> newTickets) {
            this.oldTickets = oldTickets;
            this.newTickets = newTickets;
        }

        @Override
        public int getOldListSize() {
            return oldTickets.size();
        }

        @Override
        public int getNewListSize() {
            return newTickets.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            TicketEntity oldTicket = oldTickets.get(oldItemPosition);
            TicketEntity newTicket = newTickets.get(newItemPosition);
            return oldTicket.getId() == newTicket.getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            TicketEntity oldTicket = oldTickets.get(oldItemPosition);
            TicketEntity newTicket = newTickets.get(newItemPosition);
            return oldTicket.equals(newTicket);
        }
    }

}
