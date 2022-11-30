package enemy;

import entity.Entity;
import main.GamePanel;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ENEMY_Spaceship extends Entity {
    public BufferedImage enemy;

    public ENEMY_Spaceship(GamePanel tl){
        super(tl);
        direction = "down";
        speed = 1;
        getEnemyImage();
    }

    @Override
    public void setAction()  // Movimento da nave inimiga
    {
        Random rnd = new Random();
        int i = rnd.nextInt(100)+1;
        if(i <= 25){
            direction = "up";
        }else if(i > 25 && i<=50){
            direction = "down";
        }else if(i > 50 && i<=75){
            direction = "left";
        }else if(i>75 && i<=100){
            direction = "right";
        }
    }

    public void getEnemyImage(){
        enemy = setup("/res/assetsTeste/ship_3");
    }

}
