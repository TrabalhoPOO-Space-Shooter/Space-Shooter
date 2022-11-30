package main;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel tl;
    public Font arial;
    public Font pixelated;
    public Font arialB;
    Graphics2D g2;
    public int comando = 0;

    public UI(GamePanel tl){
        this.tl = tl;
        arial = new Font("Arial", Font.PLAIN,40);
        arialB = new Font("Arial",Font.BOLD,80);
        try {
            InputStream is = getClass().getResourceAsStream("../res/font/Pixel.ttf");
            pixelated = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(@NotNull Graphics2D g2){
        this.g2 = g2;
        g2.setFont(pixelated); // Coloca a nova fonte no projeto.
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        if(tl.gameState == tl.titleState) // TELA INICIAL DO JOGO COM AS OPÇÕES
        {
            drawTitleScreen();
        }

        if(tl.gameState == tl.playState) // DÁ PLAY NO JOGO
        {
            // Faça play state
        }
        if(tl.gameState == tl.pauseState) // PAUSA O JOGO
        {
            drawPauseScreen();
        }
    }

    public void drawTitleScreen()
    {
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0,0,tl.screenWidth,tl.screenHeight);

        // Nome do jogo
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        String txt = "Space Shooter";
        int x = getXforCenteredText(txt);
        int y = tl.tileSize * 3;

        // SOMBRAS NO TITULO DA TELA PRINCIPAL
        g2.setColor(Color.gray);
        g2.drawString(txt,x+5,y+5);

        // COR PRINCIPAL
        g2.setColor(Color.white);
        g2.drawString(txt,x,y);

        // IMAGEM DA NAVE NA TELA PRINCIPAL
        x = tl.screenWidth /2 - (tl.tileSize *2)/2;
        y += tl.tileSize *2;
        g2.drawImage(tl.player.image, x,y, tl.tileSize *2, tl.tileSize *2, null);

        // MENU DA TELA PRINCIPAL
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,42F));
        txt = "NOVO JOGO";
        x = getXforCenteredText(txt);
        y+= tl.tileSize * 3.5;
        g2.drawString(txt,x,y);
        if(comando == 0){
            g2.drawString(">",x - tl.tileSize,y);
        }

        txt = "CARREGAR JOGO";
        x = getXforCenteredText(txt);
        y+= tl.tileSize + 3;
        g2.drawString(txt,x,y);
        if(comando == 1){
            g2.drawString(">",x - tl.tileSize,y);
        }

        txt = "SAIR";
        x = getXforCenteredText(txt);
        y+= tl.tileSize + 3;
        g2.drawString(txt,x,y);
        if(comando == 2){
            g2.drawString(">",x - tl.tileSize,y);
        }

    }

    public void drawPauseScreen()
    {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = tl.screenHeight /2;
        g2.drawString(text,x,y);
    }

    public int getXforCenteredText(String txt){
        int tam = (int)g2.getFontMetrics().getStringBounds(txt,g2).getWidth();
        int x = tl.screenWidth / 2 - tam/2;
        return x;
    }
}
