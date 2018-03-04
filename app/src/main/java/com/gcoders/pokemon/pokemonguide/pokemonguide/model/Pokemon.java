
package com.gcoders.pokemon.pokemonguide.pokemonguide.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Pokemon implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("num")
    @Expose
    private String num;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("type")
    @Expose
    private List<String> type = null;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("candy")
    @Expose
    private String candy;
    @SerializedName("candy_count")
    @Expose
    private Integer candyCount;
    @SerializedName("egg")
    @Expose
    private String egg;
    @SerializedName("spawn_chance")
    @Expose
    private Double spawnChance;
    @SerializedName("avg_spawns")
    @Expose
    private Double avgSpawns;
    @SerializedName("spawn_time")
    @Expose
    private String spawnTime;
    @SerializedName("multipliers")
    @Expose
    private List<Double> multipliers = null;
    @SerializedName("weaknesses")
    @Expose
    private List<String> weaknesses = null;
    @SerializedName("next_evolution")
    @Expose
    private List<NextEvolution> nextEvolution = null;
    @SerializedName("prev_evolution")
    @Expose
    private List<PrevEvolution> prevEvolution = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCandy() {
        return candy;
    }

    public void setCandy(String candy) {
        this.candy = candy;
    }

    public Integer getCandyCount() {
        return candyCount;
    }

    public void setCandyCount(Integer candyCount) {
        this.candyCount = candyCount;
    }

    public String getEgg() {
        return egg;
    }

    public void setEgg(String egg) {
        this.egg = egg;
    }

    public Double getSpawnChance() {
        return spawnChance;
    }

    public void setSpawnChance(Double spawnChance) {
        this.spawnChance = spawnChance;
    }

    public Double getAvgSpawns() {
        return avgSpawns;
    }

    public void setAvgSpawns(Double avgSpawns) {
        this.avgSpawns = avgSpawns;
    }

    public String getSpawnTime() {
        return spawnTime;
    }

    public void setSpawnTime(String spawnTime) {
        this.spawnTime = spawnTime;
    }

    public List<Double> getMultipliers() {
        return multipliers;
    }

    public void setMultipliers(List<Double> multipliers) {
        this.multipliers = multipliers;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<String> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public List<NextEvolution> getNextEvolution() {
        return nextEvolution;
    }

    public void setNextEvolution(List<NextEvolution> nextEvolution) {
        this.nextEvolution = nextEvolution;
    }

    public List<PrevEvolution> getPrevEvolution() {
        return prevEvolution;
    }

    public void setPrevEvolution(List<PrevEvolution> prevEvolution) {
        this.prevEvolution = prevEvolution;
    }

}
