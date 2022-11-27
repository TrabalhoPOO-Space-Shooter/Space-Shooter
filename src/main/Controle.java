package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controle implements KeyListener { // Implementa a interface de controle
    public boolean cima, baixo, esquerda, direita;

    @Override
    public void keyTyped(KeyEvent keyEvent) {
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
