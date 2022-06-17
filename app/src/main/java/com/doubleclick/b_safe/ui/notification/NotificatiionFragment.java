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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class NotificatiionFragment extends Fragment implements RequestAdapter.Check {

    private RecyclerView requestsRecycler;
    private RequestViewModel requestViewModel;
    private RequestAdapter requestAdapter;
    private DatabaseReference reference;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notificatiion, container, false);
        requestsRecycler = view.findViewById(R.id.requests);
        requestAdapter = new RequestAdapter();
        requestAdapter.setCheck(this);
        reference = FirebaseDatabase.getInstance().getReference();
        requestViewModel = new ViewModelProvider(this).get(RequestViewModel.class);
        requestViewModel.getRequest().observe(getViewLifecycleOwner(), new Observer<ArrayList<Requests>>() {
            @Override
            public void onChanged(ArrayList<Requests> requests) {
                requestAdapter.setRequestsArrayList(requests);
                requestsRecycler.setAdapter(requestAdapter);
            }
        });

        return view;
    }


    @Override
    public void Yes(String id, String custumerId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(FirebaseAuth.getInstance().getCurrentUser().getUid().toString(), true);
        reference.child("CenterServiecAcceptrd").setValue(map);
        reference.child("Requstes").child(id).removeValue();
    }

    @Override
    public void No(String id) {
        reference.child("Requstes").child(id).removeValue();
    }
}