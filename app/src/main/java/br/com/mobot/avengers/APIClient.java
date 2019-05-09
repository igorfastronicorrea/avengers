package br.com.mobot.avengers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static final String BASE_URL = "http://marvel-api-usc.herokuapp.com/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient(){

        Gson gson = new GsonBuilder().setLenient().create();

        OkHttpClient client = new OkHttpClient();

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
