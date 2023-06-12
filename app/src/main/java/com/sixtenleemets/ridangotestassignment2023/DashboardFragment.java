package com.sixtenleemets.ridangotestassignment2023;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.sixtenleemets.ridangotestassignment2023.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    // TODO use data to determine
    private static final boolean LIST_HAS_ENTRIES = false;

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

        // TODO use data to determine
        binding.emptyState.setVisibility(LIST_HAS_ENTRIES ? View.GONE : View.VISIBLE);
        binding.tickets.setVisibility(LIST_HAS_ENTRIES ? View.VISIBLE : View.GONE);
    }

}