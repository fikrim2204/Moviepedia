package rpl1pnp.fikri.moviepedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void popular(View view) {
        Intent i = new Intent(MainActivity.this, PopularActivity.class);
        startActivity(i);
    }
}
