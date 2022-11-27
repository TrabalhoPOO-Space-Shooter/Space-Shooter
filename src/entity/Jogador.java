package entity;

import main.Controle;
import main.TelaDeJogo;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Jogador extends Entity {
    TelaDeJogo tj;
    Controle controle;

    public Jogador(TelaDeJogo tj, Controle controle) {
        this.tj = tj;
        this.controle = controle;
        defineValorPadrao();
        imagemJogador();
    }

    public void defineValorPadrao() {
        x = 100;
        y = 100;
        velocidade = 4;
    }

    public void imagemJogador() {
        try {
            imagem = ImageIO.read(getClass().getResourceAsStream("/res/jogador/nave.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    }

    public void draw(Graphics2D g2) {
        BufferedImage img = imagem;
        g2.drawImage(img, x, y, tj.tamFinalQuadro, tj.tamFinalQuadro, null);
    }
}
