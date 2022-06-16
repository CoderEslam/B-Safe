package com.doubleclick.b_safe.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.doubleclick.b_safe.R;
import com.doubleclick.b_safe.model.ServiceCenter;
import com.mig35.carousellayoutmanager.CarouselLayoutManager;
import com.mig35.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.mig35.carousellayoutmanager.CenterScrollListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created By Eslam Ghazy on 6/14/2022
 */
public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    ArrayList<ServiceCenter> serviceCenters;

    public ServiceAdapter(ArrayList<ServiceCenter> serviceCenters) {
        this.serviceCenters = serviceCenters;
    }

    @NonNull
    @Override
    public ServiceAdapter.ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.center_servise_layout, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAdapter.ServiceViewHolder holder, int position) {
        holder.image.setAdapter(new ImageAdapter("", serviceCenters.get(position).getImages()));
        holder.name.setText(String.format("%s-%s", serviceCenters.get(position).getName(), serviceCenters.get(position).getAddress()));
        holder.rate.setRating(serviceCenters.get(position).getRate());
        holder.phone.setText(serviceCenters.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return serviceCenters.size();
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView image;
        private TextView name, phone;
        private RatingBar rate;

        public ServiceViewHolder(@NonNull View view) {
            super(view);
            image = view.findViewById(R.id.image);
            name = view.findViewById(R.id.name);
            rate = view.findViewById(R.id.rate);
            phone = view.findViewById(R.id.phone);
            CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL);
            image.setLayoutManager(layoutManager);
            image.setHasFixedSize(true);
            image.addOnScrollListener(new CenterScrollListener());
            layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());


        }
    }
}
