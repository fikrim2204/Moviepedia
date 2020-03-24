package rpl1pnp.fikri.moviepedia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import rpl1pnp.fikri.moviepedia.R;
import rpl1pnp.fikri.moviepedia.model.MovieData;
import rpl1pnp.fikri.moviepedia.network.Constant;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    private List<MovieData> movieDatas;
    private Context context;

    private OnMovieItemClickListener onMovieItemSelectedListener;

    public MovieListAdapter(Context context) {
        this.context = context;
        movieDatas = new ArrayList<>();
    }

    private void add(MovieData item) {
        movieDatas.add(item);
        notifyItemInserted(movieDatas.size() - 1);
    }

    public void addAll(List<MovieData> moviesDatas) {
        for (MovieData movieData : moviesDatas) {
            add(movieData);
        }
    }

    public void remove(MovieData item) {
        int position = movieDatas.indexOf(item);
        if (position > -1) {
            movieDatas.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    private MovieData getItem(int i) {
        return movieDatas.get(i);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,
                parent, false);
        final MovieViewHolder movieViewHolder = new MovieViewHolder(view);
        movieViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPos = movieViewHolder.getAdapterPosition();
                if (adapterPos != RecyclerView.NO_POSITION) ;
                if (onMovieItemSelectedListener != null) {
                    onMovieItemSelectedListener.onItemClick(movieViewHolder.itemView, adapterPos);
                }
            }
        });
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        final MovieData movieData = movieDatas.get(position);
        Glide.with(context).load(Constant.IMG_URL + "/w500" + movieData.getPosterPath()).into(holder.img);
//        holder.bind(movieData);
    }

    @Override
    public int getItemCount() {
        return movieDatas.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_movie);
        }

        public void bind(MovieData movieData) {

        }
    }

    public interface OnMovieItemClickListener {

        void onItemClick(View itemView, int adapterPos);
    }

    public void setOnMovieItemSelectedListener(OnMovieItemClickListener onMovieItemSelectedListener) {
        this.onMovieItemSelectedListener = onMovieItemSelectedListener;
    }
}
