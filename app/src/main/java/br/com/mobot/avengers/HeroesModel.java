package br.com.mobot.avengers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HeroesModel {

    @SerializedName("heroes")
    @Expose
    private List<HeroModel> heroes;

    public List<HeroModel> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<HeroModel> heroes) {
        this.heroes = heroes;
    }
}
