package main;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gp;
    public Font arial;
    private float closeIn = 10;
    public Font pixelated;
    public Font arialB;
    Graphics2D g2;
    public int commandNum = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial = new Font("Arial", Font.PLAIN, 40);
        arialB = new Font("Arial", Font.BOLD, 80);
        try {
            InputStream is = getClass().getResourceAsStream("../res/font/Pixel.ttf");
            pixelated = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(@NotNull Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(pixelated); // Coloca a nova fonte no projeto.
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        if (gp.gameState == gp.titleState) // TELA INICIAL DO JOGO COM AS OPÇÕES
        {
            drawTitleScreen();
        }

        if (gp.gameState == gp.playState) // DÁ PLAY NO JOGO
        {
            // Faça play state
        }
        if (gp.gameState == gp.pauseState) // PAUSA O JOGO
        {
            drawPauseScreen();
        }

        if (gp.gameState == gp.gameWin) {
            drawGameWinScreen();
        }

        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
    }

    public void drawTitleScreen() {
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Nome do jogo
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String txt = "Space Shooter";
        int x = getXforCenteredText(txt);
        int y = gp.tileSize * 3;

        // SOMBRAS NO TITULO DA TELA PRINCIPAL
        g2.setColor(Color.gray);
        g2.drawString(txt, x + 5, y + 5);

        // COR PRINCIPAL
        g2.setColor(Color.white);
        g2.drawString(txt, x, y);

        // IMAGEM DA NAVE NA TELA PRINCIPAL
        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.image, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        // MENU DA TELA PRINCIPAL
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 42F));
        txt = "NOVO JOGO";
        x = getXforCenteredText(txt);
        y += gp.tileSize * 3.5;
        g2.drawString(txt, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        txt = "CARREGAR JOGO";
        x = getXforCenteredText(txt);
        y += gp.tileSize + 3;
        g2.drawString(txt, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        txt = "SAIR";
        x = getXforCenteredText(txt);
        y += gp.tileSize + 3;
        g2.drawString(txt, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }

    }

    public void drawGameWinScreen() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
        text = "Você Ganhou!";
        // Sombra
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);
        // Letra Principal
        g2.setColor(Color.white);
        g2.drawString(text, x - 4, y - 4);
        // Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Continuar";
        x = getXforCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - 40, y);
        }
        text = "Sair";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - 40, y);
        }

    }

    public void drawGameOverScreen() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
        text = "Você perdeu!";
        // Sombra
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);
        // Letra Principal
        g2.setColor(Color.white);
        g2.drawString(text, x - 4, y - 4);
        // Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Tentar Novamente";
        x = getXforCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - 40, y);
        }
        // Quit
        text = "Sair";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - 40, y);
        }
    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    public int getXforCenteredText(String txt) {
        int tam = (int) g2.getFontMetrics().getStringBounds(txt, g2).getWidth();
        int x = gp.screenWidth / 2 - tam / 2;
        return x;
    }
}
