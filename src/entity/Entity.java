package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Entity {
    public int x, y;
    public int direction;
    public Rectangle areaSolida = new Rectangle(0, 0, 48, 48);

    GamePanel tl;
    public BufferedImage image;
    public int areaSolidaPadraoX, areaSolidaPadraoY;

    public boolean collisionOn = false;


    // Atributos da nave
    public String nome;
    public int speed;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int vidaMax;
    public int vida;
    public int ataque;
    public Projetil projetil;

    public Entity(GamePanel tl) {
        this.tl = tl;
    }


    public void setAction() {

    }

    public void update() {


    }


    public void draw(Graphics2D g2) {
        BufferedImage img = null;
        img = image;
        g2.drawImage(img, tl.tileSize * 13, tl.tileSize * 2, tl.tileSize, tl.tileSize, null);
    }


    public BufferedImage setup(String imagePath) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

}
