package com.doubleclick.b_safe.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.doubleclick.b_safe.Adapter.ServiceAdapter;
import com.doubleclick.b_safe.R;
import com.doubleclick.b_safe.ViewModel.ServiesCenterViewModel;
import com.doubleclick.b_safe.databinding.FragmentHomeBinding;
import com.doubleclick.b_safe.model.Data;
import com.doubleclick.b_safe.model.MyResponse;
import com.doubleclick.b_safe.model.Sender;
import com.doubleclick.b_safe.model.ServiceCenter;
import com.doubleclick.b_safe.model.User;
import com.doubleclick.b_safe.notification.Api.APIService;
import com.doubleclick.b_safe.notification.Client;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private DatabaseReference reference;
    private APIService apiService;
    private ServiceAdapter serviceAdapter;
    private RecyclerView centerService;
    private ServiesCenterViewModel serviesCenterViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        reference = FirebaseDatabase.getInstance().getReference();
        apiService = Client.getClient("https://fcm.googleapis.com/").create(APIService.class);
        centerService = view.findViewById(R.id.centerService);
        serviesCenterViewModel = new ViewModelProvider(this).get(ServiesCenterViewModel.class);
        serviesCenterViewModel.getServiceCenter().observe(getViewLifecycleOwner(), new Observer<ArrayList<ServiceCenter>>() {
            @Override
            public void onChanged(ArrayList<ServiceCenter> serviceCenters) {
                serviceAdapter = new ServiceAdapter(serviceCenters);
                centerService.setAdapter(serviceAdapter);
            }
        });


        return view;
    }


    public void sendNotifiaction(String receiver) {
        reference.child("Users").child(receiver).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot dataSnapshot = task.getResult();
                User user = dataSnapshot.getValue(User.class);
                Data data = new Data(FirebaseAuth.getInstance().getCurrentUser().getUid().toString(), R.drawable.email, "Hello", "B-Safe", receiver);
                Sender sender = new Sender(data, user.getToken());
                apiService.sendNotification(sender)
                        .enqueue(new Callback<MyResponse>() {
                            @Override
                            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                                if (response.code() == 200) {
                                    if (response.body().success != 1) {
                                        Toast.makeText(requireContext(), "Failed!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<MyResponse> call, Throwable t) {

                            }
                        });
            }

        });
    }


}