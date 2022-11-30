package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class    KeyHandler implements KeyListener { // Implementa a interface de controle
    GamePanel tl;
    public boolean upPressed, downPressed, leftPressed, rightPressed, shoot;

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

   public KeyHandler(GamePanel tl){
        this.tl = tl; // Necessário para o GameState de pause e start
   }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode(); // code recebe um inteiro correspondente a tecla pressionada
        // ESTADO DO TITULO
            if(tl.gameState == tl.titleState){
                if (code == KeyEvent.VK_W)
                {
                    tl.ui.comando--;
                    if(tl.ui.comando < 0){
                        tl.ui.comando = 2;
                    }
                }
                if (code == KeyEvent.VK_S)
                {
                    tl.ui.comando++;
                    if(tl.ui.comando > 2){
                        tl.ui.comando = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER){
                    if(tl.ui.comando == 0){
                        tl.gameState = tl.playState;
                    }else if(tl.ui.comando == 1){
                        // ADICIONAR DEPOIS A FUNÇÃO DE CARREGAR O JOGO
                    }else if(tl.ui.comando == 2){
                        System.exit(0);
                    }
                }
            }

        // Atirar
        if(code == KeyEvent.VK_F){
            shoot = true;
        }
        // ESTADO DO JOGO
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_ESCAPE) { //Se a tecla ESC por pressionada
                if (tl.gameState == tl.playState) {
                    tl.gameState = tl.pauseState; //Pausa o jogo se estiver jogando
                } else if (tl.gameState == tl.pauseState) {
                    tl.gameState = tl.playState;  // Des-pausa o jogo se estiver pausado.
                }
            }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode(); // code recebe um inteiro correspondente a tecla solta

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if(code == KeyEvent.VK_F){
            shoot = false;
        }
    }
}
