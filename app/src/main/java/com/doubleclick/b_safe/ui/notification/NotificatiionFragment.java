package com.doubleclick.b_safe.ui.notification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.doubleclick.b_safe.Adapter.RequestAdapter;
import com.doubleclick.b_safe.R;
import com.doubleclick.b_safe.ViewModel.RequestViewModel;
import com.doubleclick.b_safe.model.Requests;

import java.util.ArrayList;

public class NotificatiionFragment extends Fragment {

    private RecyclerView requestsRecycler;
    private RequestViewModel requestViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notificatiion, container, false);
        requestsRecycler = view.findViewById(R.id.requests);
        requestViewModel = new ViewModelProvider(this).get(RequestViewModel.class);
        requestViewModel.getRequest().observe(getViewLifecycleOwner(), new Observer<ArrayList<Requests>>() {
            @Override
            public void onChanged(ArrayList<Requests> requests) {
                requestsRecycler.setAdapter(new RequestAdapter(requests));
            }
        });

        return view;
    }


}