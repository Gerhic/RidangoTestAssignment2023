package com.sixtenleemets.ridangotestassignment2023;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.sixtenleemets.ridangotestassignment2023.data.model.TicketEntity;
import com.sixtenleemets.ridangotestassignment2023.databinding.FragmentTicketBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TicketFragment extends Fragment {

    private static final String TAG = "TicketFragment";

    @Inject
    TicketViewModel ticketViewModel;

    private FragmentTicketBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTicketBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.productName.addTextChangedListener(getTextWatcher());
        binding.price.addTextChangedListener(getTextWatcher());
        binding.buttonSell.setOnClickListener(button -> {
            button.setEnabled(false);
            closeKeyboard();
            String productName = binding.productName.getText().toString();
            String productPrice = binding.price.getText().toString();
            TicketEntity ticket = new TicketEntity(productName, Integer.parseInt(productPrice));
            ticketViewModel.addTicket(ticket);
        });

        ticketViewModel.getTicketAddedLiveData().observe(getViewLifecycleOwner(), ticketAdded -> {
            if (ticketAdded) {
                // Ticket added successfully
                NavHostFragment.findNavController(TicketFragment.this)
                        .navigate(R.id.action_TicketFragment_to_DashboardFragment);
            } else {
                // Failed to add ticket
                Snackbar.make(view, "Failed to add ticket", BaseTransientBottomBar.LENGTH_LONG)
                        .show();
                Log.w(TAG, "ticket not added");
                binding.buttonSell.setEnabled(true);
            }
        });
    }

    @NonNull
    private TextWatcher getTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                boolean hasName = binding.productName.getText().length() > 0;
                boolean hasPrice = binding.price.getText().length() > 0;
                binding.buttonSell.setEnabled(hasName && hasPrice);
            }
        };
    }

    private void closeKeyboard() {
        View view = getView();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}