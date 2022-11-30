package main;

import enemy.ENEMY_Spaceship;

public class AssetSetter {
    GamePanel tl;

    public AssetSetter(GamePanel tl){
        this.tl = tl;
    }

    public void setEnemy(){
        tl.enemy[0] = new ENEMY_Spaceship(tl);
        tl.enemy[0].x = tl.tileSize *21;
        tl.enemy[0].y = tl.tileSize *21;

    }
}
