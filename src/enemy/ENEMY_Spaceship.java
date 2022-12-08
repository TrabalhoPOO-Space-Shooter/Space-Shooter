package enemy;

import entity.Entity;
import main.GamePanel;
import org.jetbrains.annotations.NotNull;

import java.awt.Graphics2D;

public class ENEMY_Spaceship extends Entity {

    int tamFixoX = 32;
    int tamFixoY = 32;

    public ENEMY_Spaceship(GamePanel tl, int iniX, int iniY, int direction) {
        super(tl);
        speed = 2;
        this.x = iniX;
        this.y = iniY;
        this.direction = direction;
        image = setup("../res/assetsTeste/ship_1");
    }

    public void update() {
        x += speed * direction;
        if (x > 768) {
            x = 0;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTam() {
        return 14; // tamanho do inimigo
    }

    public void swapDirection() {
        direction = direction * -1;
        y += 25;
        speed += 0.55f;
    }

    public void draw(@NotNull Graphics2D g2) {
        g2.drawImage(image, x, y, tamFixoX, tamFixoY, null);
    }

}
