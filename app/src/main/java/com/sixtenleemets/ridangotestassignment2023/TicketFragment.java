package com.sixtenleemets.ridangotestassignment2023;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.sixtenleemets.ridangotestassignment2023.databinding.FragmentTicketBinding;

public class TicketFragment extends Fragment {

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
        binding.buttonSell.setOnClickListener(button ->
                NavHostFragment.findNavController(TicketFragment.this)
                        .navigate(R.id.action_TicketFragment_to_DashboardFragment));
        binding.productName.addTextChangedListener(getTextWatcher());
        binding.price.addTextChangedListener(getTextWatcher());
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

}