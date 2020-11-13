package com.example.teama;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.MyViewHolder> {

    private Context mContext ;
    private List<Board_Gallery> mData ;


    public BoardAdapter(Context mContext, List<Board_Gallery> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.card_board,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.recipe_title.setText(mData.get(position).getTitle());
        holder.recipe_image.setImageResource(mData.get(position).getThumbnail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,BoardActivity.class);

                // passing data to the book activity
                intent.putExtra("Title",mData.get(position).getTitle());
                intent.putExtra("Description",mData.get(position).getDescription());
                intent.putExtra("Thumbnail",mData.get(position).getThumbnail());
                // start the activity
                mContext.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView recipe_title;
        ImageView recipe_image;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);

            recipe_title = (TextView) itemView.findViewById(R.id.recipe_title_id) ;
            recipe_image = (ImageView) itemView.findViewById(R.id.board_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);


        }
    }


}