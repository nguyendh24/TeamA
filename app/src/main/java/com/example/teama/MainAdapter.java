package com.example.teama;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ImageHolder> {

    private List<Integer> images;
    private String[] titles = {"Chicken", "Keto", "Pescatarian", "Vegetarian", "Gluten-Free"};
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public MainAdapter(List<Integer> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_main, parent, false);
        ImageHolder imageHolder = new ImageHolder(view, mListener);
        return imageHolder;
    }

    /* Aligns the array of images with the array of diet titles */
    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {
        int image_id = images.get(position);
        holder.diet_image.setImageResource(image_id);
        holder.diet_type.setText(titles[position] + " Recipes");
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    /* Place holder and setter for the cards in main view */
    public static class ImageHolder extends RecyclerView.ViewHolder {
        ImageView diet_image;
        TextView diet_type;

        /* Must pass in OnItemClickListener because this class recommended to remain static
        * and so we can access mListener in setOnClickListener */
        public ImageHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            diet_image = itemView.findViewById(R.id.recipe_img_id);
            diet_type = itemView.findViewById(R.id.diet_name);

            /* Making itemView clickable */
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) { //TO MAKE SURE POSITION IS VALID
                            listener.onItemClick(position); //WILL PASS THIS POSITION TO INTERFACE METHOD
                        }
                    }
                }
            });
        }
    }
}
