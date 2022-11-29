package entity;

import main.Controle;
import main.TelaDeJogo;
import object.OBJ_Laser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Jogador extends Entity {
    TelaDeJogo tj;
    Controle controle;

    public final int telaX;
    public final int telaY;

    public Jogador(TelaDeJogo tj, Controle controle) {
        super(tj); // Inicializa a classe Entity
        this.tj = tj;
        this.controle = controle;

        telaX = tl.larguraTela/2 - (tl.tamFinalQuadro/2);
        telaY = tl.alturaTela/2 - (tl.tamFinalQuadro/2);

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
        velocidade = 4;
        vidaMax = 4;
        vida = vidaMax;
        projetil = new OBJ_Laser(tl);
    }

    public void imagemJogador() {
            imagem = setup("/res/assetsTeste/ship_2"); // Função da Entity class, para alterar imagem do jogador

    }

    public void update() {
        if (controle.cima) {
            y -= velocidade;
        } else if (controle.baixo) {
            y += velocidade;
        } else if (controle.esquerda) {
            x -= velocidade;
        } else if (controle.direita) {
            x += velocidade;
        }

        // Collision checker

        collisionOn = false;
        tl.cChecker.checkTile(this);
    }

    public void draw(Graphics2D g2) {
        BufferedImage img = imagem;
        g2.drawImage(img, x, y, tj.tamFinalQuadro, tj.tamFinalQuadro, null);
    }
}
