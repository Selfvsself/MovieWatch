package com.selfvsself.moviewatch.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.selfvsself.moviewatch.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private List<Movie> movieList;

    public RecyclerAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.movieList = movieList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textTitle.setText(movieList.get(position).getTitle());
        holder.textGenre.setText(movieList.get(position).getGenre());
        holder.textDescription.setText(movieList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setList(List<Movie> list) {
        movieList = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        final TextView textTitle, textGenre, textDescription;
        public CardView viewForeground, viewBackground;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.item_title);
            textGenre = itemView.findViewById(R.id.item_genre);
            textDescription = itemView.findViewById(R.id.item_description);
            viewForeground = itemView.findViewById(R.id.itemCardView);
        }
    }
}
