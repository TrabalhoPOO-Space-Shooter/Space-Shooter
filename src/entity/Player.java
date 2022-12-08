package entity;

import main.KeyHandler;
import main.GamePanel;
import object.OBJ_Laser;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyHandler;
    public boolean canShoot;
    private int gameTime;
    public ArrayList<Projetil> tiros;
    public Projetil tiro;
    public final int telaX;
    public final int telaY;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        super(gp); // Inicializa a classe Entity
        this.gp = gp;
        this.keyHandler = keyHandler;

        telaX = tl.screenWidth / 2 - (tl.tileSize / 2);
        telaY = tl.screenHeight / 2 - (tl.tileSize / 2);

        areaSolida = new Rectangle();
        areaSolida.x = 8;
        areaSolida.y = 16;
        areaSolidaPadraoX = areaSolida.x;
        areaSolidaPadraoY = areaSolida.y;
        areaSolida.width = 32;
        areaSolida.height = 32;
        projetil = new OBJ_Laser(gp);
        defineValorPadrao();
        imagemJogador();
    }

    public Projetil shoot() {
        canShoot = false;
        tiro = new Projetil(tl, x + 20, y - 30);
        return tiro;
    }

    public void defineValorPadrao() {
        tiros = new ArrayList<Projetil>();
        x = 380;
        y = 500;
        gameTime = 0;
        speed = 4;
        vidaMax = 4;
        vida = vidaMax;
        projetil = new OBJ_Laser(tl);
    }

    public void imagemJogador() {
        image = setup("/res/assetsTeste/ship_2"); // Função da Entity class, para alterar imagem do jogador

    }

    public void update() {
        if (keyHandler.leftPressed) {
            x -= speed;
        } else if (keyHandler.rightPressed) {
            x += speed;
        }
        // Atirar
        if (gp.keyH.shootPressed && canShoot) {
            tiros.add(this.shoot());
            canShoot = false;
        }
        if (gameTime >= 30) {
            canShoot = true;
            gameTime = 0;
        }
        gameTime++;
    }

    public void draw(@NotNull Graphics2D g2) {
        BufferedImage image = this.image;
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
