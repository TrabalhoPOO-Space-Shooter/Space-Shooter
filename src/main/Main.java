package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame(); // Instanciação da classe JFrame
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Permite fechar a janela
        window.setResizable(false); // Proíbe alterar o tamanho da janela
        window.setTitle("Space Shooter"); // Dá nome a janela

        GamePanel gamePanel = new GamePanel(); // Instanciando a classe tela de jogo

        window.add(gamePanel); // Adiciona a tela de jogo à janela que será mostrada no PC
        window.pack(); // Faz a janela usar as configurações predefinidas
        window.setLocationRelativeTo(null); // Abre a janela no centro da tela do PC
        window.setVisible(true); // Permite ver a janela na tela do PC
        gamePanel.configGame();
        gamePanel.startGameThread();
    }
}
