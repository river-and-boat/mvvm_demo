package com.thoughtworks.mvvmrecycleview.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.thoughtworks.mvvmrecycleview.GalleryActivity;
import com.thoughtworks.mvvmrecycleview.R;
import com.thoughtworks.mvvmrecycleview.models.NicePlace;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private ArrayList<NicePlace> nicePlaces = new ArrayList<>();
    private Context context;

    public RecycleViewAdapter(Context context, ArrayList<NicePlace> nicePlaces) {
        this.nicePlaces = nicePlaces;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item,
                parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).asBitmap()
                .load(nicePlaces.get(position).getImageUrl())
                .into(holder.circleImageView);
        holder.imageName.setText(nicePlaces.get(position).getTitle());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, nicePlaces.get(position).getTitle(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, GalleryActivity.class);
                intent.putExtra("image_url", nicePlaces.get(position).getImageUrl());
                intent.putExtra("image_name", nicePlaces.get(position).getTitle());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nicePlaces.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView circleImageView;
        private TextView imageName;
        private RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            relativeLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
