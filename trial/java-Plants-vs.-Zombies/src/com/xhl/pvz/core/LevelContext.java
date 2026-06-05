package com.xhl.pvz.core;

// 为什么 新添了 这个类 是因为 写 sunflower的时候 ，产生阳光是需要往草坪上生成物品，这个update 就不仅仅是他自己内部的 属性变化了，还需要和外界进行交互
// 嗯，降低耦合的 作用，
import com.xhl.pvz.manager.EntityManager;
import com.xhl.pvz.model.SunResource; // 这个是用来统计阳光数量的类

public class LevelContext {
    private final EntityManager entityManager;
    private final SunResource sunResource;
    private boolean gameOverRequested =false; // 这个量在这里完全是因为 这个量是通过update来更新的，

    // private final EntityManager entityManager;
    // private final SunResource sunResource;
    public LevelContext(EntityManager entityManager,SunResource sunResource){
        this.entityManager=entityManager;
        this.sunResource=sunResource;
    }

    public EntityManager getEntityManager(){
        return entityManager;
    }

    public SunResource getSunResource(){
        return sunResource;
    }
    public void requestGameOver(){
        gameOverRequested =true;
    }
    public boolean isGameOverRequested(){
        return gameOverRequested;
    }
}
