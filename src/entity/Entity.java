package entity;

import main.TelaDeJogo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Entity {
    public int x, y;
    public String direcao;
    public Rectangle areaSolida = new Rectangle(0,0,48,48);

    TelaDeJogo tl;
    public BufferedImage imagem;
    public int areaSolidaPadraoX, areaSolidaPadraoY;

    public boolean collisionOn = false;


    // Atributos da nave
    public String nome;
    public int velocidade;
    public int vidaMax;
    public int vida;
    public int ataque;

    public Projetil projetil;

    public Entity(TelaDeJogo tl){
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
        int screenX = x - tl.jogador.x + tl.jogador.telaX;
        int screenY = y - tl.jogador.x + tl.jogador.telaY;
        if(x + tl.tamFinalQuadro > tl.jogador.x - tl.jogador.telaX &&
           x - tl.tamFinalQuadro < tl.jogador.x + tl.jogador.telaX &&
           y + tl.tamFinalQuadro > tl.jogador.y - tl.jogador.telaY &&
           y - tl.tamFinalQuadro < tl.jogador.y + tl.jogador.telaY
        ){

        }
        g2.drawImage(img,tl.tamFinalQuadro*13,tl.tamFinalQuadro*2, tl.tamFinalQuadro, tl.tamFinalQuadro, null);
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
