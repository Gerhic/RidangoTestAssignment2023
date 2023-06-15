package com.sixtenleemets.ridangotestassignment2023;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.sixtenleemets.ridangotestassignment2023.adapter.TicketAdapter;
import com.sixtenleemets.ridangotestassignment2023.databinding.FragmentDashboardBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DashboardFragment extends Fragment {

    private static final String TAG = "DashboardFragment";

    @Inject
    TicketAdapter ticketAdapter;

    @Inject
    TicketViewModel ticketViewModel;

    private FragmentDashboardBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.addTicket.setOnClickListener(fab ->
                NavHostFragment.findNavController(DashboardFragment.this)
                        .navigate(R.id.action_DashboardFragment_to_TicketFragment));

        binding.tickets.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.tickets.setAdapter(ticketAdapter);

        ticketViewModel.getTicketsLiveData().observe(getViewLifecycleOwner(), tickets -> {
            Log.d(TAG, "onViewCreated: tickets count is " + tickets.size());
            ticketAdapter.setTickets(tickets);
            updateTicktsVisibility(tickets.size());
        });
    }

    private void updateTicktsVisibility(int count) {
        binding.emptyState.setVisibility(count > 0 ? View.GONE : View.VISIBLE);
        binding.tickets.setVisibility(count > 0 ? View.VISIBLE : View.GONE);
    }

}