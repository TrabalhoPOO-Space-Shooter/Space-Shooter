package entity;

import enemy.ENEMY_Spaceship;
import main.GamePanel;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Projetil extends  Entity {
    private int tamX = 3;
    private int tamY = 15;
    public Projetil(GamePanel tl, int iniX, int iniY){
        super(tl);
        this.x = iniX;
        this.y = iniY;
        speed = 6;
        image = setup("../res/assetsTeste/laser");
    }

    public void draw(@NotNull Graphics2D g2){
        g2.drawImage(image,x,y,null);
    }

    public void update(){
        y-=speed;
    }

    public boolean destroy(){
        return y < 0;
    }

    public boolean collideWith(ENEMY_Spaceship enemy){
        if(x>= enemy.getX() && x + tamX < enemy.getX() + enemy.getTam()){
            if(y <= enemy.getY() + enemy.getTam()){
                return true;
            }
        }
        return false;
    }

}
