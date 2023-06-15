package com.sixtenleemets.ridangotestassignment2023;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.sixtenleemets.ridangotestassignment2023.data.model.Ticket;
import com.sixtenleemets.ridangotestassignment2023.databinding.FragmentTicketBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

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
            String productName = binding.productName.getText().toString();
            String productPrice = binding.price.getText().toString();
            Ticket ticket = new Ticket(productName, Float.parseFloat(productPrice));
            addTicket(ticket);
        });
    }

    private void addTicket(Ticket ticket) {
        Completable.fromAction(() -> ticketViewModel.addTicket(ticket))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "addTicket onSubscribe");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "addTicket onComplete");
                        NavHostFragment.findNavController(TicketFragment.this)
                                .navigate(R.id.action_TicketFragment_to_DashboardFragment);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.w(TAG, "addTicket onError: ", e);
                        binding.buttonSell.setEnabled(true);
                        Snackbar.make(
                                binding.buttonSell,
                                e.toString(),
                                BaseTransientBottomBar.LENGTH_LONG
                        ).show();
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

}