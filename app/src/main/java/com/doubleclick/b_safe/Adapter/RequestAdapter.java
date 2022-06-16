package com.doubleclick.b_safe.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieAnimationView;
import com.doubleclick.b_safe.R;
import com.doubleclick.b_safe.model.Requests;
import com.doubleclick.b_safe.ui.Servies.ViewRequestActivity;

import java.util.ArrayList;

/**
 * Created By Eslam Ghazy on 6/16/2022
 */
public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {


    private ArrayList<Requests> requestsArrayList;

    public RequestAdapter(ArrayList<Requests> requestsArrayList) {
        this.requestsArrayList = requestsArrayList;
    }

    @NonNull
    @Override
    public RequestAdapter.RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RequestViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.request_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RequestAdapter.RequestViewHolder holder, int position) {
        holder.attchementImage.setAdapter(new ImageAdapter("", requestsArrayList.get(position).getImages()));
        holder.yes.setOnClickListener(view -> {

        });
        holder.no.setOnClickListener(view -> {

        });
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), ViewRequestActivity.class);
            intent.putExtra("request", requestsArrayList.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return requestsArrayList.size();
    }

    public class RequestViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView attchementImage;
        private LottieAnimationView yes, no;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            attchementImage = itemView.findViewById(R.id.attchementImage);
            yes = itemView.findViewById(R.id.animationViewyes);
            no = itemView.findViewById(R.id.animationViewNo);
        }

    }
}
