package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener { // Implementa a interface de controle
    GamePanel gp;
    public boolean leftPressed, rightPressed, shootPressed;

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    public KeyHandler(GamePanel gp) {
        this.gp = gp; // Necessário para o GameState de pause e start
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode(); // code recebe um inteiro correspondente a tecla pressionada
        // ESTADO DO TITULO
        if (gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.resetAll();
                    gp.gameState = gp.playState;
                } else if (gp.ui.commandNum == 1) {
                    gp.saveLoad.load();
                    gp.gameState = gp.playState;
                } else if (gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        }

        // ESTADO DO JOGO
        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            // Atirar
            if (code == KeyEvent.VK_F) {
                shootPressed = true;
            }
            if (code == KeyEvent.VK_ESCAPE) { //Se a tecla ESC por pressionada
                if (gp.gameState == gp.playState) {
                    gp.gameState = gp.pauseState; //Pausa o jogo se estiver jogando
                } else if (gp.gameState == gp.pauseState) {
                    gp.gameState = gp.playState;  // Des-pausa o jogo se estiver pausado.
                }
            }
        }

        // Estado de Game Win
        if (gp.gameState == gp.gameWin) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 1;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 1) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.levelUp();
                    gp.reset();
                    gp.saveLoad.save();
                    gp.gameState = gp.playState;
                } else if (gp.ui.commandNum == 1) {
                    gp.gameState = gp.titleState;
                }
            }
        }

        // Estado de Game Over
        if (gp.gameState == gp.gameOverState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 1;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 1) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                gp.resetAll();
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                } else if (gp.ui.commandNum == 1) {
                    gp.gameState = gp.titleState;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode(); // code recebe um inteiro correspondente a tecla solta
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_F) {
            shootPressed = false;
        }
    }
}
