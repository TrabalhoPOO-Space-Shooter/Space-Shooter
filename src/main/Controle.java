package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controle implements KeyListener { // Implementa a interface de controle
    TelaDeJogo tl;
    public boolean cima, baixo, esquerda, direita;

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

   public Controle(TelaDeJogo tl){
        this.tl = tl; // Necessário para o GameState de pause e start
   }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode(); // code recebe um inteiro correspondente a tecla pressionada

        if (code == KeyEvent.VK_W) {
            cima = true;
        }
        if (code == KeyEvent.VK_S) {
            baixo = true;
        }
        if (code == KeyEvent.VK_A) {
            esquerda = true;
        }
        if (code == KeyEvent.VK_D) {
            direita = true;
        }
        if (code == KeyEvent.VK_ESCAPE) { //Se a tecla ESC por pressionada
            if(tl.gameState == tl.playState){
                tl.gameState = tl.pauseState; //Pausa o jogo se estiver jogando
            }else if(tl.gameState == tl.pauseState){
                tl.gameState = tl.playState;  // Des-pausa o jogo se estiver pausado.
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode(); // code recebe um inteiro correspondente a tecla solta

        if (code == KeyEvent.VK_W) {
            cima = false;
        }
        if (code == KeyEvent.VK_S) {
            baixo = false;
        }
        if (code == KeyEvent.VK_A) {
            esquerda = false;
        }
        if (code == KeyEvent.VK_D) {
            direita = false;
        }
    }
}
