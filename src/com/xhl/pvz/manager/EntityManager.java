package com.xhl.pvz.manager;

import com.xhl.pvz.core.LevelContext;
import com.xhl.pvz.entity.bullet.Bullet;
import com.xhl.pvz.entity.item.Sun;
import com.xhl.pvz.entity.plant.Plant;
import com.xhl.pvz.entity.zombie.Zombie;
import java.awt.Graphics2D;
import java.util.ArrayList; // 不是 java 里面 还有 迭代器?!
import java.util.Iterator;
import java.util.List;

// 这个类 中 实现了 什么？ 又是 怎么 和其他的 类 进行分工协调的？
// 现在 这个 实体管理类 这个时候 只是对植物进行了 管理， 主要 负责  所有 植物实体的 画面 渲染，检查，更新，检测 功能，结合 前面的 SceneManager 类，我知道Manager类 只要就是负责渲染，更新操作，至于 每个个体类，都用了 插槽机制 来处理相类的 不同情形
// 这个类 是用在了 LevelScene 里面 也就是 主游戏场景的 逻辑类里面，所以 只能说 这个类的 功能 在 主游戏 场景 中 实现的
public class EntityManager {
    private final List<Plant> plants=new ArrayList<>();
    private final List<Sun> suns = new ArrayList<>();
    private final List<Zombie> zombies = new ArrayList<>();
    private final List<Bullet> bullets = new ArrayList<>();

    public void addPlant(Plant plant){
        if(plant !=null){
            plants.add(plant);
        }
    }
    public void addZombie(Zombie zombie) {
        if (zombie != null) {
            zombies.add(zombie);
        }
    }
    public void updateAll(LevelContext context){
        for(Plant plant: plants){
            plant.update(context);
        }
        for(Sun sun:suns){
            sun.update(context);
        }
        for (Zombie zombie : zombies) {
            zombie.update(context);
        }
        for (Bullet bullet : bullets) {
            bullet.update(context);
        }
        // removeDeadEntities();
    }

    public Plant getCollidingPlant(Zombie zombie) {
        //他这些交互的函数基本上是交给这个类写的
        if (zombie == null || !zombie.isAlive()) {
            return null;
        }

        for (Plant plant : plants) {
            if (!plant.isAlive()) {
                continue;
            }

            if (plant.getRow() != zombie.getRow()) {
                continue;
            }

            if (zombie.getBounds().intersects(plant.getBounds())) { // Entity 中方法中的Rectangle 中的函数成员
                // 这个方法 是为了判断两个Rectangle类会不会重叠
                return plant;
            }
        }

        return null;
    }
    public void renderAll(Graphics2D g){
        for(Plant plant:plants){
            plant.render(g);
        }
        for (Sun sun : suns) {
            sun.render(g);
        }
        for (Zombie zombie : zombies) {
            zombie.render(g);
        }
        for (Bullet bullet : bullets) {
            bullet.render(g);
        }
    }

    public void addSun(Sun sun) {
        if (sun != null) {
            suns.add(sun);
        }
    }
    public void addBullet(Bullet bullet) {
       if (bullet != null) {
            bullets.add(bullet);
        }
    }
    public void removeDeadEntities(){
        Iterator<Plant> iterator = plants.iterator();
        while(iterator.hasNext()){ // 说明 什么 ？ iterator 最开始指的 是 begin() 的 前一个位置
            Plant plant = iterator.next();
            
            if(!plant.isAlive()){
                iterator.remove(); // 直接就 把 这个 删除了，
            }
        }
        Iterator<Zombie> zombieIterator = zombies.iterator();

        while (zombieIterator.hasNext()) {
            Zombie zombie = zombieIterator.next();

            if (!zombie.isAlive()) {
                zombieIterator.remove();
            }
        }
        Iterator<Bullet> bulletIterator = bullets.iterator();

        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();

            if (!bullet.isAlive()) {
                bulletIterator.remove();
            }
        }
        Iterator<Sun> sunIterator = suns.iterator();

        while (sunIterator.hasNext()) {
            Sun sun = sunIterator.next();

            if (!sun.isAlive()) {
                sunIterator.remove();
            }
        }
    }
    public boolean hasPlantAt(int row,int col){ // 这个函数的 功能是什么 ? 这个函数 应该是检测点击的 这个 位置有没有 植物

        for(Plant plant:plants){
            if(plant.getRow() == row && plant.getCol() ==col &&plant.isAlive()){
                return true;
            }
        }
        return false;
    }

    public Plant getPlantAt(int row,int col){  //这个 函数 和 上面的 函数 搭配使用
        for(Plant plant :plants){
            if(plant.getRow()== row && plant.getCol()==col&&plant.isAlive()){
                return plant;
            }
        }
        return null;
    }
    public Sun getSunAt(int mouseX, int mouseY) {
        for (int i = suns.size() - 1; i >= 0; i--) {
            Sun sun = suns.get(i);

            if (sun.isAlive() && sun.contains(mouseX, mouseY)) {
                return sun;
            }
        }

        return null;
    }
    public boolean hasZombieInRow(int row, double plantX) {
        for (Zombie zombie : zombies) {
            if (zombie.isAlive()&& zombie.getRow() == row&& zombie.getX() > plantX) {
                return true;
            }
        }

        return false;
    }
    public List<Plant> getPlants(){
        return plants;
    }
    public List<Zombie> getZombies() {
        return zombies;
    }
    public List<Bullet> getBullets() {
        return bullets;
    }

    public void clearAll() {
        plants.clear();
        zombies.clear();
        bullets.clear();
        suns.clear();
    }

}
