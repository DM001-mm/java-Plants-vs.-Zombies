package com.xhl.pvz.manager;

// import com.xhl.pvz.entity.Entity;
import com.xhl.pvz.entity.bullet.Bullet;
import com.xhl.pvz.entity.zombie.Zombie;
// import java.text.CollationElementIterator;

public class CollisionManager {
    private final EntityManager entityManager;
    public CollisionManager(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    public void checkAll(){
        checkBulletZombieCollisions();
    }

    private void checkBulletZombieCollisions(){
        for(Bullet bullet: entityManager.getBullets()){
            if(!bullet.isAlive()){
                continue ;
            }

            for(Zombie zombie:entityManager.getZombies()){
                if(!zombie.isAlive()){
                    continue ;
                }
                
                if(bullet.getRow()!=zombie.getRow()){
                    continue ;
                }
                
                if (bullet.getBounds().intersects(zombie.getBounds())) {
                    zombie.takeDamage(bullet.getDamage());
                    bullet.setAlive(false);

                    AudioManager.playEffect("pea_hit");

                    System.out.println("豌豆击中僵尸，造成伤害: " + bullet.getDamage());

                    // 一颗子弹只打一个僵尸
                    break;
                }
            }
        }
    }
}
