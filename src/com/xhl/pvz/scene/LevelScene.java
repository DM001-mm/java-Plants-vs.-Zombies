package com.xhl.pvz.scene;

import com.xhl.pvz.core.GameConfig;
import com.xhl.pvz.core.LevelContext;
import com.xhl.pvz.core.SceneManager;
import com.xhl.pvz.entity.bullet.Bullet;
import com.xhl.pvz.entity.bullet.PeaBullet;
import com.xhl.pvz.entity.item.Sun;
import com.xhl.pvz.entity.plant.Plant;
import com.xhl.pvz.entity.zombie.Zombie;
import com.xhl.pvz.factory.BulletFactory;
import com.xhl.pvz.factory.PlantCardFactory;
import com.xhl.pvz.factory.PlantFactory;
import com.xhl.pvz.factory.ZombieFactory;
import com.xhl.pvz.manager.AudioManager;
import com.xhl.pvz.manager.CollisionManager;
import com.xhl.pvz.manager.EntityManager;
import com.xhl.pvz.manager.ImageManager;
import com.xhl.pvz.manager.LevelManager;
import com.xhl.pvz.manager.SaveManager;
import com.xhl.pvz.model.SunResource;
import com.xhl.pvz.save.BulletSaveData;
import com.xhl.pvz.save.PlantSaveData;
import com.xhl.pvz.save.SaveData;
import com.xhl.pvz.save.SunSaveData;
import com.xhl.pvz.save.ZombieSaveData;
import com.xhl.pvz.ui.PauseMenuUI;
import com.xhl.pvz.ui.PlantCard;
import com.xhl.pvz.ui.SunBankUI;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class LevelScene extends BaseScene {

    private final SceneManager sceneManager;

    private BufferedImage background;

    private LevelManager levelManager;
    private EntityManager entityManager;
    private CollisionManager collisionManager;
    private LevelContext levelContext;

    private SunResource sunResource;
    private SunBankUI sunBankUI;

    private PlantCard peashooterCard;
    private PlantCard sunflowerCard;

    private String selectedPlantType = null;
    private PlantCard selectedCard = null;

    private boolean paused = false;
    private PauseMenuUI pauseMenuUI;

    private final int rowCount = 5;
    private final int colCount = 9;

    private final int gridStartX = 170;
    private final int gridStartY = 120;
    private final int cellWidth = 80;
    private final int cellHeight = 90;

    public LevelScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void onEnter() {
        if (ImageManager.hasImage("background.lawn_day")) {
            background = ImageManager.getImage("background.lawn_day");
        }

        entityManager = new EntityManager();
        collisionManager = new CollisionManager(entityManager);

        sunResource = new SunResource(150);
        levelContext = new LevelContext(entityManager, sunResource);

        sunBankUI = new SunBankUI(20, 15, 120, 60, sunResource);

        peashooterCard = PlantCardFactory.createPeashooterCard(160, 15);
        sunflowerCard = PlantCardFactory.createSunflowerCard(240, 15);

        levelManager = new LevelManager(gridStartY, cellHeight);

        pauseMenuUI = new PauseMenuUI();
        paused = false;

        selectedPlantType = null;
        selectedCard = null;

        AudioManager.playBGM("level_day");
    }

    @Override
    public void update() {
        if (paused) {
            return;
        }

        peashooterCard.update();
        sunflowerCard.update();

        levelManager.update(levelContext);

        entityManager.updateAll(levelContext);

        collisionManager.checkAll();

        entityManager.removeDeadEntities();

        checkLevelResult();
    }

    @Override
    public void render(Graphics2D g) {
        drawBackground(g);

        sunBankUI.render(g);

        peashooterCard.render(g);
        sunflowerCard.render(g);

        entityManager.renderAll(g);

        drawDebugGrid(g);

        if (paused && pauseMenuUI != null) {
            pauseMenuUI.render(g);
        }
    }

    @Override
    public void onMousePressed(int x, int y) {
        if (paused) {
            handlePauseMenuClick(x, y);
            return;
        }

        if (handleSunClick(x, y)) {
            return;
        }

        if (peashooterCard.contains(x, y)) {
            handleCardClick(peashooterCard);
            return;
        }

        if (sunflowerCard.contains(x, y)) {
            handleCardClick(sunflowerCard);
            return;
        }

        int row = getRowByY(y);
        int col = getColByX(x);

        if (row != -1 && col != -1) {
            handleLawnClick(row, col);
        }
    }

    @Override
    public void onKeyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            paused = !paused;
            return;
        }

        if (keyCode == KeyEvent.VK_S) {
            saveGame();
            return;
        }

        if (keyCode == KeyEvent.VK_L) {
            loadGame();
        }
    }

    private void handlePauseMenuClick(int x, int y) {
        if (pauseMenuUI.isContinueButtonClicked(x, y)) {
            paused = false;
            AudioManager.playEffect("click");
            return;
        }

        if (pauseMenuUI.isSaveButtonClicked(x, y)) {
            saveGame();
            AudioManager.playEffect("click");
            return;
        }

        if (pauseMenuUI.isLoadButtonClicked(x, y)) {
            loadGame();
            AudioManager.playEffect("click");
            paused = false;
            return;
        }

        if (pauseMenuUI.isMenuButtonClicked(x, y)) {
            AudioManager.playEffect("click");
            sceneManager.changeScene(new MainMenuScene(sceneManager));
        }
    }

    private boolean handleSunClick(int x, int y) {
        Sun sun = entityManager.getSunAt(x, y);

        if (sun == null) {
            return false;
        }

        sunResource.add(sun.getValue());
        sun.collect();

        AudioManager.playEffect("sun_collect");

        System.out.println("收集阳光 +" + sun.getValue());

        return true;
    }

    private void handleCardClick(PlantCard card) {
        if (!card.canUse(sunResource.getAmount())) {
            System.out.println("阳光不足或卡片正在冷却！");
            AudioManager.playEffect("card_error");
            return;
        }

        if (selectedCard != null) {
            selectedCard.setSelected(false);
        }

        selectedPlantType = card.getPlantType();
        selectedCard = card;
        selectedCard.setSelected(true);

        System.out.println("选中植物: " + selectedPlantType);
        AudioManager.playEffect("card_select");
    }

    private void handleLawnClick(int row, int col) {
        if (selectedPlantType == null || selectedCard == null) {
            return;
        }

        if (entityManager.hasPlantAt(row, col)) {
            System.out.println("这个格子已经有植物了");
            AudioManager.playEffect("card_error");
            return;
        }

        if (!sunResource.canAfford(selectedCard.getCost())) {
            System.out.println("阳光不足!");
            AudioManager.playEffect("card_error");
            return;
        }

        int plantX = gridStartX + col * cellWidth;
        int plantY = gridStartY + row * cellHeight + 5;

        Plant plant = PlantFactory.createPlant(
                selectedPlantType,
                row,
                col,
                plantX,
                plantY
        );

        if (plant == null) {
            return;
        }

        entityManager.addPlant(plant);

        System.out.println("放置植物: " + selectedPlantType + ", row = " + row + ", col = " + col);

        sunResource.spend(selectedCard.getCost());
        selectedCard.startCooldown();

        AudioManager.playEffect("plant_place");

        selectedCard.setSelected(false);
        selectedPlantType = null;
        selectedCard = null;
    }

    private void drawBackground(Graphics2D g) {
        if (background != null) {
            g.drawImage(
                    background,
                    0,
                    0,
                    GameConfig.WINDOW_WIDTH,
                    GameConfig.WINDOW_HEIGHT,
                    null
            );
        } else {
            Color oldColor = g.getColor();

            g.setColor(new Color(80, 160, 80));
            g.fillRect(0, 0, GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);

            g.setColor(oldColor);
        }
    }

    private void drawDebugGrid(Graphics2D g) {
        Color oldColor = g.getColor();

        g.setColor(Color.BLACK);

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                int x = gridStartX + col * cellWidth;
                int y = gridStartY + row * cellHeight;
                g.drawRect(x, y, cellWidth, cellHeight);
            }
        }

        g.setColor(oldColor);
    }

    private int getRowByY(int y) {
        if (y < gridStartY || y >= gridStartY + rowCount * cellHeight) {
            return -1;
        }

        return (y - gridStartY) / cellHeight;
    }

    private int getColByX(int x) {
        if (x < gridStartX || x >= gridStartX + colCount * cellWidth) {
            return -1;
        }

        return (x - gridStartX) / cellWidth;
    }

    private void checkLevelResult() {
        if (levelContext.isGameOverRequested()) {
            sceneManager.changeScene(new GameOverScene(sceneManager));
            return;
        }

        if (levelManager.isAllZombiesSpawned()
                && entityManager.getZombies().isEmpty()) {
            sceneManager.changeScene(new WinScene(sceneManager));
        }
    }

    private void saveGame() {
        SaveData saveData = new SaveData();

        saveData.setSunAmount(sunResource.getAmount());
        saveData.setLevelTick(levelManager.getTick());

        for (Plant plant : entityManager.getPlants()) {
            PlantSaveData plantSaveData = new PlantSaveData(
                    PlantFactory.getPlantType(plant),
                    plant.getRow(),
                    plant.getCol(),
                    plant.getHp()
            );

            saveData.addPlant(plantSaveData);
        }

        for (Zombie zombie : entityManager.getZombies()) {
            ZombieSaveData zombieSaveData = new ZombieSaveData(
                    ZombieFactory.getZombieType(zombie),
                    zombie.getRow(),
                    zombie.getX(),
                    zombie.getY(),
                    zombie.getHp()
            );

            saveData.addZombie(zombieSaveData);
        }

        for (Bullet bullet : entityManager.getBullets()) {
            BulletSaveData bulletSaveData = new BulletSaveData(
                    BulletFactory.getBulletType(bullet),
                    bullet.getRow(),
                    bullet.getX(),
                    bullet.getY()
            );

            saveData.addBullet(bulletSaveData);
        }

        for (Sun sun : entityManager.getSuns()) {
            SunSaveData sunSaveData = new SunSaveData(
                    sun.getX(),
                    sun.getY()
            );

            saveData.addSun(sunSaveData);
        }

        SaveManager.save(saveData, "save1.dat");
    }

    private void loadGame() {
        SaveData saveData = SaveManager.load("save1.dat");

        if (saveData == null) {
            return;
        }

        entityManager.clearAll();

        sunResource.setAmount(saveData.getSunAmount());
        levelManager.setTick(saveData.getLevelTick());

        for (PlantSaveData plantData : saveData.getPlants()) {
            int row = plantData.getRow();
            int col = plantData.getCol();

            int plantX = gridStartX + col * cellWidth;
            int plantY = gridStartY + row * cellHeight + 5;

            Plant plant = createPlantFromSaveData(plantData, plantX, plantY);

            if (plant != null) {
                entityManager.addPlant(plant);
            }
        }

        for (ZombieSaveData zombieData : saveData.getZombies()) {
            Zombie zombie = createZombieFromSaveData(zombieData);

            if (zombie != null) {
                entityManager.addZombie(zombie);
            }
        }

        for (BulletSaveData bulletData : saveData.getBullets()) {
            Bullet bullet = createBulletFromSaveData(bulletData);

            if (bullet != null) {
                entityManager.addBullet(bullet);
            }
        }

        for (SunSaveData sunData : saveData.getSuns()) {
            Sun sun = new Sun(sunData.getX(), sunData.getY());
            entityManager.addSun(sun);
        }

        if (selectedCard != null) {
            selectedCard.setSelected(false);
        }

        selectedCard = null;
        selectedPlantType = null;

        System.out.println("读档完成");
    }
    private Plant createPlantFromSaveData(PlantSaveData plantData, int x, int y) {
        Plant plant = PlantFactory.createPlant(
                plantData.getPlantType(),
                plantData.getRow(),
                plantData.getCol(),
                x,
                y
        );

        if (plant != null) {
            plant.setHp(plantData.getHp());
        }

        return plant;
    }

    private Zombie createZombieFromSaveData(ZombieSaveData data) {
        Zombie zombie = ZombieFactory.createZombie(
                data.getZombieType(),
                data.getRow(),
                data.getX(),
                data.getY()
        );

        if (zombie != null) {
            zombie.setHp(data.getHp());
        }

        return zombie;
    }

    private Bullet createBulletFromSaveData(BulletSaveData data) {
        if ("PeaBullet".equals(data.getBulletType())) {
            return new PeaBullet(
                    data.getRow(),
                    data.getX(),
                    data.getY()
            );
        }

        return null;
    }
}