package entity;

import main.GamePanel;
import org.jetbrains.annotations.NotNull;

import java.awt.Graphics2D;

public class Explosions extends Entity {
    private int animationTot;
    private int line,col;
    public int duration;
    public Explosions(GamePanel tl, int x, int y){
        super(tl);
        image = setup("../res/assetsTeste/explosion");
        this.x = x;
        this.y = y;
        animationTot = 180; // 2 segundos
        duration = 0;
        line = 0;
        col = 0;
    }

    public void update(){

        duration--;
        line = duration / 6;
        col = duration % 5;
    }

    public boolean explosionEnds(){
        if(duration >= animationTot){
            return true;
        }
        return false;
    }

    public void draw(@NotNull Graphics2D g2){

        g2.drawImage(image,x,y,x +50, y+50,192 * col,192 * line,192 * col + 192,192 * line + 192,null);
    }
}
