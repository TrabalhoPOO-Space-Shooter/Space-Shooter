package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Entity {
    public int x, y;
    public String direction;
    public Rectangle areaSolida = new Rectangle(0,0,48,48);

    GamePanel tl;
    public BufferedImage image;
    public int areaSolidaPadraoX, areaSolidaPadraoY;

    public boolean collisionOn = false;


    // Atributos da nave
    public String nome;
    public int speed;
    public int vidaMax;
    public int vida;
    public int ataque;

    public Projetil projetil;

    public Entity(GamePanel tl){
        this.tl = tl;
    }


    public void setAction(){

    }

    public void update(){
        setAction(); // Está vazio, mas o setAction que é usado é o declarado em ENEMY_Spaceship
        collisionOn = false;
        // tl.cC
    }


    public void draw(Graphics2D g2){
        BufferedImage img = null;
        img = setup("/res/assetsTeste/ship_1");
        int screenX = x - tl.player.x + tl.player.telaX;
        int screenY = y - tl.player.x + tl.player.telaY;
        if(x + tl.tileSize > tl.player.x - tl.player.telaX &&
           x - tl.tileSize < tl.player.x + tl.player.telaX &&
           y + tl.tileSize > tl.player.y - tl.player.telaY &&
           y - tl.tileSize < tl.player.y + tl.player.telaY
        ){

        }
        g2.drawImage(img,tl.tileSize *13,tl.tileSize *2, tl.tileSize, tl.tileSize, null);
    }


    public BufferedImage setup(String imagePath){
        BufferedImage img = null;
        try{
            img = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        return img;
    }

}
