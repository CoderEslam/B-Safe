package com.doubleclick.b_safe.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.doubleclick.b_safe.JoinUsActivity;
import com.doubleclick.b_safe.R;
import com.doubleclick.b_safe.ViewModel.UserViewModel;
import com.doubleclick.b_safe.model.User;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {


    private TextView joinUs;
    private CircleImageView image;
    private TextInputEditText name, phone, email, vehicleModel;
    private Button save;
    private UserViewModel userViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        joinUs = view.findViewById(R.id.joinUs);
        image = view.findViewById(R.id.image);
        name = view.findViewById(R.id.name);
        phone = view.findViewById(R.id.phone);
        email = view.findViewById(R.id.email);
        vehicleModel = view.findViewById(R.id.vehicleModel);
        save = view.findViewById(R.id.save);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getMyUser().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                name.setText(user.getName());
                phone.setText(user.getPhone());
                email.setText(user.getEmail());
                vehicleModel.setText(user.getVehicleModel());
                Glide.with(requireContext()).load(user.getImage()).into(image);
            }
        });

        joinUs.setOnClickListener(v -> {
            startActivity(new Intent(requireContext(), JoinUsActivity.class));
        });

        return view;
    }


}