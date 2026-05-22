package com.xhl.pvz.scene;

import com.xhl.pvz.core.GameConfig;
import com.xhl.pvz.core.LevelContext;
import com.xhl.pvz.core.SceneManager;
import com.xhl.pvz.entity.bullet.Bullet;
import com.xhl.pvz.entity.item.Sun;
import com.xhl.pvz.entity.plant.Plant;
import com.xhl.pvz.entity.zombie.Zombie;
import com.xhl.pvz.factory.BulletFactory;
import com.xhl.pvz.factory.PlantCardFactory;
import com.xhl.pvz.factory.PlantFactory;
import com.xhl.pvz.factory.ZombieFactory;
import com.xhl.pvz.lawn.Grid;
import com.xhl.pvz.manager.AudioManager;
import com.xhl.pvz.manager.CollisionManager;
import com.xhl.pvz.manager.EntityManager;
import com.xhl.pvz.manager.ImageManager;
import com.xhl.pvz.manager.LevelManager;
import com.xhl.pvz.manager.SaveManager;
import com.xhl.pvz.manager.SkySunSpawner;
import com.xhl.pvz.model.SunResource;
import com.xhl.pvz.resource.ImageKeys;
import com.xhl.pvz.save.BulletSaveData;
import com.xhl.pvz.save.PlantSaveData;
import com.xhl.pvz.save.SaveData;
import com.xhl.pvz.save.SunSaveData;
import com.xhl.pvz.save.ZombieSaveData;
import com.xhl.pvz.ui.CardBarUI;
import com.xhl.pvz.ui.PauseMenuUI;
import com.xhl.pvz.ui.PlantCard;
import com.xhl.pvz.ui.ShovelUI;
import com.xhl.pvz.ui.StatusMessageUI;
import com.xhl.pvz.ui.SunBankUI;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class LevelScene extends BaseScene {

    private final SceneManager sceneManager;

    private BufferedImage background;

    private Grid grid;

    private LevelManager levelManager;
    private EntityManager entityManager;
    private CollisionManager collisionManager;
    private LevelContext levelContext;

    private SunResource sunResource;
    private SunBankUI sunBankUI;
    private SkySunSpawner skySunSpawner;

    private CardBarUI cardBarUI;

    private String selectedPlantType = null;
    private PlantCard selectedCard = null;

    private boolean paused = false;
    private PauseMenuUI pauseMenuUI;

    private StatusMessageUI statusMessageUI;

    private ShovelUI shovelUI;
    private boolean shovelMode = false;
    
    public LevelScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void onEnter() {
        grid = new Grid(
                5,
                9,
                170,
                120,
                80,
                90
        );
        shovelUI = new ShovelUI(760, 15, 70, 70);
        shovelMode = false;
        if (ImageManager.hasImage(ImageKeys.BACKGROUND_LAWN_DAY)) {
            background = ImageManager.getImage(ImageKeys.BACKGROUND_LAWN_DAY);
        }

        entityManager = new EntityManager();
        collisionManager = new CollisionManager(entityManager);

        sunResource = new SunResource(150);
        levelContext = new LevelContext(entityManager, sunResource);

        sunBankUI = new SunBankUI(20, 15, 120, 60, sunResource);

        cardBarUI = new CardBarUI();
        cardBarUI.addCard(PlantCardFactory.createPeashooterCard(160, 15));
        cardBarUI.addCard(PlantCardFactory.createSunflowerCard(240, 15));

        levelManager = new LevelManager(
                grid.getStartY(),
                grid.getCellHeight()
        );

        skySunSpawner = new SkySunSpawner(
                300,
                grid.getStartX(),
                grid.getRightX() - 50,
                grid.getStartY(),
                grid.getBottomY() - 80
        );

        pauseMenuUI = new PauseMenuUI();
        statusMessageUI = new StatusMessageUI(330, 560);

        paused = false;
        selectedPlantType = null;
        selectedCard = null;

        AudioManager.playBGM("level_day");
    }

    @Override
    public void update() {
        statusMessageUI.update();

        if (paused) {
            return;
        }

        cardBarUI.update();


        levelManager.update(levelContext);
        skySunSpawner.update(levelContext);

        entityManager.updateAll(levelContext);

        collisionManager.checkAll();

        entityManager.removeDeadEntities();

        checkLevelResult();
    }

    @Override
    public void render(Graphics2D g) {
        drawBackground(g);

        sunBankUI.render(g);

        cardBarUI.render(g);
        shovelUI.render(g);

        entityManager.renderAll(g);

        drawDebugGrid(g);

        if (paused && pauseMenuUI != null) {
            pauseMenuUI.render(g);
        }

        statusMessageUI.render(g);
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
        if (shovelUI.contains(x, y)) {
            toggleShovelMode();
            return;
        }
        PlantCard clickedCard = cardBarUI.getClickedCard(x, y);

        if (clickedCard != null) {
            handleCardClick(clickedCard);
            return;
        }

        int row = grid.getRowByY(y);
        int col = grid.getColByX(x);

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
    private void toggleShovelMode() {
        shovelMode = !shovelMode;
        shovelUI.setSelected(shovelMode);

        if (shovelMode) {
            cardBarUI.clearSelection();

            selectedCard = null;
            selectedPlantType = null;

            statusMessageUI.showMessage("进入铲子模式");
        } else {
            statusMessageUI.showMessage("退出铲子模式");
        }

        AudioManager.playEffect("click");
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
        statusMessageUI.showMessage("收集阳光 +" + sun.getValue());

        return true;
    }

    private void handleCardClick(PlantCard card) {
        if (!card.canUse(sunResource.getAmount())) {
            statusMessageUI.showMessage("阳光不足或正在冷却");
            AudioManager.playEffect("card_error");
            return;
        }

        cardBarUI.clearSelection();

        selectedPlantType = card.getPlantType();
        selectedCard = card;
        selectedCard.setSelected(true);

        statusMessageUI.showMessage("选中植物: " + selectedPlantType);
        AudioManager.playEffect("card_select");
    }

    private void handleLawnClick(int row, int col) {
        if (shovelMode) {
            handleShovelClick(row, col);
            return;
        }
        if (selectedPlantType == null || selectedCard == null) {
            return;
        }

        if (entityManager.hasPlantAt(row, col)) {
            statusMessageUI.showMessage("这个格子已经有植物了");
            AudioManager.playEffect("card_error");
            return;
        }

        if (!sunResource.canAfford(selectedCard.getCost())) {
            statusMessageUI.showMessage("阳光不足");
            AudioManager.playEffect("card_error");
            return;
        }

        int plantX = grid.getPlantX(col);
        int plantY = grid.getPlantY(row);

        Plant plant = PlantFactory.createPlant(
                selectedPlantType,
                row,
                col,
                plantX,
                plantY
        );

        if (plant == null) {
            statusMessageUI.showMessage("未知植物类型");
            AudioManager.playEffect("card_error");
            return;
        }

        entityManager.addPlant(plant);

        sunResource.spend(selectedCard.getCost());
        selectedCard.startCooldown();

        AudioManager.playEffect("plant_place");
        statusMessageUI.showMessage("放置植物成功");

        selectedCard.setSelected(false);
        selectedPlantType = null;
        selectedCard = null;
    }
    private void handleShovelClick(int row, int col) {
        Plant plant = entityManager.getPlantAt(row, col);

        if (plant == null) {
            statusMessageUI.showMessage("这个格子没有植物");
            AudioManager.playEffect("card_error");
            return;
        }

        entityManager.removePlantAt(row, col);

        shovelMode = false;
        shovelUI.setSelected(false);

        AudioManager.playEffect("plant_remove");
        statusMessageUI.showMessage("铲除植物成功");
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
        grid.renderDebugGrid(g);
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
        statusMessageUI.showMessage("保存成功");
    }

    private void loadGame() {
        SaveData saveData = SaveManager.load("save1.dat");

        if (saveData == null) {
            statusMessageUI.showMessage("没有找到存档");
            return;
        }

        entityManager.clearAll();

        sunResource.setAmount(saveData.getSunAmount());
        levelManager.setTick(saveData.getLevelTick());

        for (PlantSaveData plantData : saveData.getPlants()) {
            int row = plantData.getRow();
            int col = plantData.getCol();

            int plantX = grid.getPlantX(col);
            int plantY = grid.getPlantY(row);

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

        cardBarUI.clearSelection();
        selectedCard = null;
        selectedPlantType = null;

        statusMessageUI.showMessage("读档完成");
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
        return BulletFactory.createBullet(
                data.getBulletType(),
                data.getRow(),
                data.getX(),
                data.getY()
        );
    }
}