package src.save;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SaveData implements Serializable {

    private static final long serialVersionUID = 1L;

    private int sunAmount;
    private int levelTick;

    private final List<PlantSaveData> plants = new ArrayList<>();
    private final List<ZombieSaveData> zombies = new ArrayList<>();
    private final List<BulletSaveData> bullets = new ArrayList<>();
    private final List<SunSaveData> suns = new ArrayList<>();
    private List<String> selectedPlantTypes = new ArrayList<>();

    public int getSunAmount() {
        return sunAmount;
    }

    public void setSunAmount(int sunAmount) {
        this.sunAmount = sunAmount;
    }

    public int getLevelTick() {
        return levelTick;
    }

    public void setLevelTick(int levelTick) {
        this.levelTick = levelTick;
    }

    public List<PlantSaveData> getPlants() {
        return plants;
    }

    public List<ZombieSaveData> getZombies() {
        return zombies;
    }

    public List<BulletSaveData> getBullets() {
        return bullets;
    }

    public List<SunSaveData> getSuns() {
        return suns;
    }

    public List<String> getSelectedPlantTypes() {
        if (selectedPlantTypes == null) {
            selectedPlantTypes = new ArrayList<>();
        }

        return selectedPlantTypes;
    }

    public void addPlant(PlantSaveData data) {
        if (data != null) {
            plants.add(data);
        }
    }

    public void addZombie(ZombieSaveData data) {
        if (data != null) {
            zombies.add(data);
        }
    }

    public void addBullet(BulletSaveData data) {
        if (data != null) {
            bullets.add(data);
        }
    }

    public void addSun(SunSaveData data) {
        if (data != null) {
            suns.add(data);
        }
    }

    public void addSelectedPlantType(String plantType) {
        if (plantType != null) {
            getSelectedPlantTypes().add(plantType);
        }
    }
}
