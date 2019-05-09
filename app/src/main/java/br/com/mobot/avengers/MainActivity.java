package br.com.mobot.avengers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvHeroes;
    private LinearLayoutManager linearLayoutManager;
    private APIInterface apiInterface;
    private HeroesAdapter adapter;
    private List<HeroModel> dataHeroes;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listheroes);

        rvHeroes = findViewById(R.id.rvHeroes);
        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        progressBar.setVisibility(View.VISIBLE);
        getHeroes();
    }

    private void getHeroes(){
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<HeroesModel> call = apiInterface.getHeroes();

        call.enqueue(new Callback<HeroesModel>() {
            @Override
            public void onResponse(Call<HeroesModel> call, Response<HeroesModel> response) {

                dataHeroes = response.body().getHeroes();

                linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                adapter = new HeroesAdapter(getApplicationContext(), dataHeroes);
                rvHeroes.setFocusable(false);
                rvHeroes.setHasFixedSize(true);
                rvHeroes.setLayoutManager(linearLayoutManager);
                rvHeroes.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<HeroesModel> call, Throwable t) {
                Log.e("TAG", "erro");
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
