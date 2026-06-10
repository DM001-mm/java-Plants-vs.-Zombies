package src.manager;

// import src.entity.Entity;
import src.entity.bullet.Bullet;
import src.entity.zombie.Zombie;
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

            if(!bullet.canHit()){
                continue ;
            }

            for(Zombie zombie:entityManager.getZombies()){
                if(!zombie.isAlive()){
                    continue ;
                }
                
                if(bullet.getRow()!=zombie.getRow()){
                    continue ;
                }
                
                if (bullet.getCollisionBounds().intersects(zombie.getCollisionBounds())) {
                    zombie.takeDamage(bullet.getDamage());
                    bullet.onHit();

                    AudioManager.playEffect("pea_hit");

                    System.out.println("豌豆击中僵尸，造成伤害: " + bullet.getDamage());

                    // 一颗子弹只打一个僵尸
                    break;
                }
            }
        }
    }
}
