package main;

import java.awt.*;

public class UI {
    TelaDeJogo tl;
    public Font arial;
    public Font arialB;
    Graphics2D g2;

    public UI(TelaDeJogo tl){
        this.tl = tl;
        arial = new Font("Arial", Font.PLAIN,40);
        arialB = new Font("Arial",Font.BOLD,80);
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial);
        g2.setColor(Color.white);
        if(tl.gameState == tl.playState){
            // Faça play state
        }
        if(tl.gameState == tl.pauseState){
            drawPauseScreen();
        }
    }

    public void drawPauseScreen()
    {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = tl.alturaTela/2;
        g2.drawString(text,x,y);
    }

    public int getXforCenteredText(String txt){
        int tam = (int)g2.getFontMetrics().getStringBounds(txt,g2).getWidth();
        int x = tl.larguraTela / 2 - tam/2;
        return x;
    }
}
