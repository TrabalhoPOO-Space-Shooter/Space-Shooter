package entity;

import main.KeyHandler;
import main.GamePanel;
import object.OBJ_Laser;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyHandler;

    public final int telaX;
    public final int telaY;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        super(gp); // Inicializa a classe Entity
        this.gp = gp;
        this.keyHandler = keyHandler;

        telaX = tl.screenWidth /2 - (tl.tileSize /2);
        telaY = tl.screenHeight /2 - (tl.tileSize /2);

        areaSolida = new Rectangle();
        areaSolida.x = 8;
        areaSolida.y = 16;
        areaSolidaPadraoX = areaSolida.x;
        areaSolidaPadraoY  = areaSolida.y;
        areaSolida.width = 32;
        areaSolida.height = 32;
        defineValorPadrao();
        imagemJogador();
    }

    public void defineValorPadrao() {
        x = 100;
        y = 100;
        speed = 4;
        vidaMax = 4;
        vida = vidaMax;
        projetil = new OBJ_Laser(tl);
    }

    public void imagemJogador() {
            image = setup("/res/assetsTeste/ship_2"); // Função da Entity class, para alterar imagem do jogador

    }

    public void update() {
        if (keyHandler.upPressed) {
            y -= speed;
        } else if (keyHandler.downPressed) {
            y += speed;
        } else if (keyHandler.leftPressed) {
            x -= speed;
        } else if (keyHandler.rightPressed) {
            x += speed;
        }

        // Collision checker

        collisionOn = false;
        tl.cChecker.checkTile(this);
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = this.image;
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
