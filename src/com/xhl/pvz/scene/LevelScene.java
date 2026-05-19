package com.xhl.pvz.scene;

import com.xhl.pvz.core.GameConfig;
import com.xhl.pvz.core.LevelContext;
import com.xhl.pvz.core.SceneManager;
import com.xhl.pvz.entity.plant.Peashooter;
import com.xhl.pvz.entity.plant.Sunflower;
import com.xhl.pvz.manager.AudioManager;
import com.xhl.pvz.manager.CollisionManager;
import com.xhl.pvz.manager.EntityManager;
import com.xhl.pvz.manager.ImageManager;
import com.xhl.pvz.manager.LevelManager;
import com.xhl.pvz.model.SunResource;
import com.xhl.pvz.ui.PlantCard;
import com.xhl.pvz.ui.SunBankUI;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

// import java.awt.event.keyEvent;

public class LevelScene extends BaseScene {

    private final SceneManager sceneManager;
    private BufferedImage background ;
    private LevelManager levelManager;
    private EntityManager entityManager;
    private SunResource sunResource;
    private SunBankUI sunBankUI;
    private PlantCard sunflowerCard;
    private LevelContext levelContext;
    private PlantCard peashooterCard;
    private CollisionManager collisionManager;
    private String selectedPlantType =null;
    private PlantCard selectedCard = null;
    private final int rowCount =5;
    private final int colCount =9;

    private final int gridStartX = 170;
    private final int gridStartY = 120;
    private final int cellWidth = 80;
    private final int cellHeight =90;
    
    // 测试阳光数
    // private int sunCount = 50;

    public LevelScene(SceneManager sceneManager){
        this.sceneManager=sceneManager;
    }
    @Override
    public void onEnter(){
        background = ImageManager.getImage("background.lawn_day");
        levelManager = new LevelManager(gridStartY, cellHeight);
        entityManager= new EntityManager(); // 对象 
        sunResource = new SunResource(150);
        levelContext = new LevelContext(entityManager, sunResource);
        sunBankUI = new SunBankUI(20,15,120,60,sunResource);
        peashooterCard =new PlantCard("Peashooter",160,15,70,90,100,150,ImageManager.hasImage("card.peashooter")?ImageManager.getImage("card.peashooter"):null,ImageManager.hasImage("ui.cooldown_mask")?ImageManager.getImage("ui.cooldown_mask"):null);
        AudioManager.playBGM("level_day");
        sunflowerCard = new PlantCard(
            "Sunflower",
            240,
            15,
            70,
            90,
            50,
            150,
            ImageManager.hasImage("card.sunflower") ? ImageManager.getImage("card.sunflower") : null,
            ImageManager.hasImage("ui.cooldown_mask") ? ImageManager.getImage("ui.cooldown_mask") : null
        );
        collisionManager = new CollisionManager(entityManager);
    }
    @Override
    public void update(){ // 现在的update中的函数调用，僵尸吃植物的检测是放在了僵尸的update里面，植物好像也是这样的，至于子弹其实也可以这样，然后 在后面直接 check，剔除所有的死了的，或者失效的对象
        // 后面更新：
        // entityManager.updateAll();
        peashooterCard.update();
        sunflowerCard.update();
        levelManager.update(levelContext);
        entityManager.updateAll(levelContext);
        collisionManager.checkAll(); // 这是碰撞检测，
        entityManager.removeDeadEntities(); // 这是剔除
        //LevelManager.update();
        checkLevelResult();
    }
    @Override
    public void render(Graphics2D g){
        drawBackground(g);
        sunBankUI.render(g); 
        peashooterCard.render(g);
        entityManager.renderAll(g);

        // 调试格子
        drawDebugGrid(g);

    }

    @Override
    public void onMousePressed(int x,int y){
        if(peashooterCard.contains(x,y)){
            handleCardClick(peashooterCard);
        }
        int row = getRowByY(y);
        int col=getColByX(x);

        if(row!=-1&&col!=-1){
            handleLawnClick(row,col); // 两个函数 通过中间变量联系起来
        }
        // if(row ==-1||col ==-1){
        //     return ;
        // }
        // if(entityManager.hasPlantAt(row,col)){
        //     System.out.println("这个格子已经有植物了！");
        //     return ;
        // }

        // int plantX = gridStartX+col*cellWidth;
        // int plantY = gridStartY+row*cellHeight+5;
        // // 但是1 这个 为什么 需要 获取 植物的 实际位置呢
        // Peashooter peashooter =new Peashooter(row,col,plantX,plantY);//这显然是 在通过点击 种 射手
        // entityManager.addPlant(peashooter);

        // System.out.println("放置豌豆射手: row= "+row+", col = "+col);
    }

    @Override
    public void onKeyPressed(int keyCode){
        if(keyCode == KeyEvent.VK_ESCAPE){
            // System.out.println("按下ESC,返回主菜单");
            sceneManager.changeScene(new MainMenuScene(sceneManager));
        }
    }

    private void handleCardClick(PlantCard card){
        if(!card.canUse(sunResource.getAmount())){
            System.out.println("阳光不足 或卡片正在冷却！");
            AudioManager.playEffect("card_error");
            return ;
        }
        if(selectedCard!=null)
            selectedCard.setSelected(false);
        selectedPlantType = card.getPlantType();
        selectedCard =card;
        selectedCard.setSelected(true);

        System.out.println("选中植物: "+selectedPlantType);
        AudioManager.playEffect("card_select");
    }
    private void handleLawnClick(int row,int col){
        if(selectedPlantType ==null||selectedCard==null) return ;
        if(entityManager.hasPlantAt(row,col)){
            System.out.println("这个格子已经有植物了");
            AudioManager.playEffect("card_error");
            return ;
        }

        if(!sunResource.canAfford(selectedCard.getCost())){
            System.out.println("阳光不足!");
            AudioManager.playEffect("card_error");
            return ;
        }

        int plantX = gridStartX + col * cellWidth;
        int plantY = gridStartY + row * cellHeight + 5;

        if ("Peashooter".equals(selectedPlantType)) {
            Peashooter peashooter = new Peashooter(row, col, plantX, plantY);
            entityManager.addPlant(peashooter);

            System.out.println("放置豌豆射手: row = " + row + ", col = " + col);
        } else if ("Sunflower".equals(selectedPlantType)) {
            Sunflower sunflower = new Sunflower(row, col, plantX, plantY);
            entityManager.addPlant(sunflower);

            System.out.println("放置向日葵: row = " + row + ", col = " + col);
        }

        sunResource.spend(selectedCard.getCost());
        selectedCard.startCooldown();

        AudioManager.playEffect("plant_place");

        selectedCard.setSelected(false);
        selectedPlantType = null;
        selectedCard = null;
    }
    private void drawBackground(Graphics2D g){
        if(background!=null) g.drawImage(background,0,0,GameConfig.WINDOW_WIDTH,GameConfig.WINDOW_HEIGHT,null);
        else{
            g.setColor(new Color(80,160,80));
            g.fillRect(0,0,GameConfig.WINDOW_WIDTH,GameConfig.WINDOW_HEIGHT);
        }
    }

    private void drawDebugGrid(Graphics2D g){
        g.setColor(Color.BLACK); // 这里为什么会新添加这样的东西？
        for(int row = 0;row<rowCount;row++){
            for(int col =0;col<colCount;col++){
                int x = gridStartX+col*cellWidth;
                int y = gridStartY+row*cellHeight;
                g.drawRect(x,y,cellWidth,cellHeight);
            }
        }
    }

    private int getRowByY(int y){
        if(y<gridStartY||y>=gridStartY+rowCount*cellHeight){
            return -1;
        }
        return (y-gridStartY)/cellHeight;
    }

    private int getColByX(int x){
        if(x<gridStartX||x>=gridStartX+colCount*cellWidth){
            return -1;
        }
        return (x-gridStartX)/cellWidth;
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
    
}
