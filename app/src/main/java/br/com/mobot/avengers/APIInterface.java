package br.com.mobot.avengers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("heroes/")
    Call<HeroesModel> getHeroes();
}
