package com.example.teama;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ImageHolder> {

    private int[] images;
    private String[] titles = {"Chicken", "Keto", "Pescatarian", "Vegetarian", "Gluten-Free"};

    public RecyclerAdapter(int[] images) {
        this.images = images;
    }
    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
       ImageHolder imageHolder = new ImageHolder(view);

        return imageHolder;
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {
        int image_id = images[position];
        holder.diet_image.setImageResource(image_id);
        holder.diet_type.setText(titles[position] + " Recipes");
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class ImageHolder extends RecyclerView.ViewHolder {
        ImageView diet_image;
        TextView diet_type;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);
            diet_image = itemView.findViewById(R.id.recipe_img_id);
            diet_type = itemView.findViewById(R.id.diet_name);
        }
    }
}
