package src.manager;

// import src.entity.Entity;
import src.entity.bullet.Bullet;
import src.entity.bullet.IcePeaBullet;
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

                    if (bullet instanceof IcePeaBullet) {
                        IcePeaBullet iceBullet = (IcePeaBullet) bullet;
                        zombie.applySlow(
                                iceBullet.getSlowDuration(),
                                iceBullet.getSlowFactor()
                        );

                        AudioManager.playEffect("ice_hit");
                        System.out.println(
                                "寒冰豌豆击中僵尸，造成伤害: "
                                        + bullet.getDamage()
                                        + "，减速系数: "
                                        + iceBullet.getSlowFactor()
                                        + "，持续: "
                                        + iceBullet.getSlowDuration()
                        );
                    } else {
                        AudioManager.playEffect("pea_hit");
                        System.out.println("豌豆击中僵尸，造成伤害: " + bullet.getDamage());
                    }

                    bullet.onHit();

                    // 一颗子弹只打一个僵尸
                    break;
                }
            }
        }
    }
}
