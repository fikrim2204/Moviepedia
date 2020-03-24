package rpl1pnp.fikri.moviepedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rpl1pnp.fikri.moviepedia.adapter.MovieListAdapter;
import rpl1pnp.fikri.moviepedia.model.Movie;
import rpl1pnp.fikri.moviepedia.network.ApiService;

public class PopularActivity extends AppCompatActivity {
    private MovieListAdapter movieListAdapter;
    private RecyclerView recyclerView;
    private int page = 1;
    private GridLayoutManager gridLayoutManager;
    private ApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);
        movieListAdapter = new MovieListAdapter(this);
        recyclerView = findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(movieListAdapter);

        loadData();
    }

    private void loadData() {
        service = new ApiService();
        service.getPopularMovies(page, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Movie movie = (Movie) response.body();
                if (movie != null) {
                    if (movieListAdapter != null) {
                        movieListAdapter.addAll(movie.getResults());
                        Log.e("MovieListAdapter", "Data Masuk");
                    }
                } else {
                    Toast.makeText(getBaseContext(), "No Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    Toast.makeText(getBaseContext(),
                            "Request Timeout.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getBaseContext(),
                            "Connection Error!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
