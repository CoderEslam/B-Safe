package com.doubleclick.b_safe.ui.Servies;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.doubleclick.b_safe.Adapter.ImageAdapter;
import com.doubleclick.b_safe.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;


public class ServiesFragment extends Fragment {


    public ServiesFragment() {
        // Required empty public constructor
    }

    private EditText phone, name, address;
    private RecyclerView images;
    private ArrayList<Uri> uriArrayList = new ArrayList<>();
    private ArrayList<String> urlImages = new ArrayList<>();
    private FloatingActionButton pickup;
    private Button upload;

    private DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_servies, container, false);
        reference = FirebaseDatabase.getInstance().getReference();
        images = view.findViewById(R.id.images);
        phone = view.findViewById(R.id.phone);
        name = view.findViewById(R.id.nameOfPlace);
        address = view.findViewById(R.id.address);
        pickup = view.findViewById(R.id.pickup);
        upload = view.findViewById(R.id.upload);
        pickup.setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            startActivityForResult(intent, 100);
        });

        upload.setOnClickListener(view1 -> {
            Upload();
        });


        return view;
    }

    private void Upload() {
        if (uriArrayList.size() != 0) {
            ProgressDialog progressDialog = new ProgressDialog(requireContext());
            progressDialog.show();
            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("ImagesService");
            for (Uri uri : uriArrayList) {
                storageReference.child("" + System.currentTimeMillis() + ".jpg").putFile(uri).addOnSuccessListener(taskSnapshot -> {
                    Task<Uri> task = taskSnapshot.getStorage().getDownloadUrl();
                    task.addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                Log.e("URLLLLLL", task.getResult().toString());
                                urlImages.add(task.getResult().toString());
                                if (uriArrayList.size() == urlImages.size()) {
                                    String id = reference.push().getKey().toString();
                                    HashMap<String, Object> map = new HashMap<>();
                                    map.put("address", address.getText().toString());
                                    map.put("name", name.getText().toString());
                                    map.put("phone", phone.getText().toString());
                                    map.put("images", urlImages.toString());
                                    map.put("id", id);
                                    reference.child("CenterServies").child(id).setValue(map);
                                    progressDialog.dismiss();
                                }
                            }
                        }
                    });

                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double p = 100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount();
                        progressDialog.setMessage(p + " % Uploading...");

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(requireContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                });
            }


        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == Activity.RESULT_OK && data != null) {
            ClipData clipData = data.getClipData();
            if (clipData != null) {
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    uriArrayList.add(clipData.getItemAt(i).getUri());
                }
                images.setAdapter(new ImageAdapter(uriArrayList, "uri"));
            }
        }

    }
}