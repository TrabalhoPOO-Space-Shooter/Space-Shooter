package enemy;

import entity.Entity;
import main.TelaDeJogo;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ENEMY_Spaceship extends Entity {
    public BufferedImage enemy;

    public ENEMY_Spaceship(TelaDeJogo tl){
        super(tl);
        direcao = "down";
        velocidade = 1;
        getEnemyImage();
    }

    @Override
    public void setAction()  // Movimento da nave inimiga
    {
        Random rnd = new Random();
        int i = rnd.nextInt(100)+1;
        if(i <= 25){
            direcao = "up";
        }else if(i > 25 && i<=50){
            direcao = "down";
        }else if(i > 50 && i<=75){
            direcao = "left";
        }else if(i>75 && i<=100){
            direcao = "right";
        }
    }

    public void getEnemyImage(){
        enemy = setup("/res/assetsTeste/ship_3");
    }

}
