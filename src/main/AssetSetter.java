package main;

import enemy.ENEMY_Spaceship;

public class AssetSetter {
    TelaDeJogo tl;

    public AssetSetter(TelaDeJogo tl){
        this.tl = tl;
    }

    public void setEnemy(){
        tl.enemy[0] = new ENEMY_Spaceship(tl);
        tl.enemy[0].x = tl.tamFinalQuadro*21;
        tl.enemy[0].y = tl.tamFinalQuadro*21;

    }
}
