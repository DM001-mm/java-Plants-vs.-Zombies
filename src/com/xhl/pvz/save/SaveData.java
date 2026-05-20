package com.xhl.pvz.save;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SaveData implements Serializable {

    private static final long serialVersionUID = 1L;

    private int sunAmount;

    private List<PlantSaveData> plants = new ArrayList<>();

    public int getSunAmount() {
        return sunAmount;
    }

    public void setSunAmount(int sunAmount) {
        this.sunAmount = sunAmount;
    }

    public List<PlantSaveData> getPlants() {
        return plants;
    }

    public void addPlant(PlantSaveData plantSaveData) {
        if (plantSaveData != null) {
            plants.add(plantSaveData);
        }
    }
}