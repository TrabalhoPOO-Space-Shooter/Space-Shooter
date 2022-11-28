package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        JFrame janela = new JFrame(); // Instanciação da classe JFrame
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Permite fechar a janela
        janela.setResizable(false); // Proíbe alterar o tamanho da janela
        janela.setTitle("Space Shooter"); // Dá nome a janela

        TelaDeJogo telaDeJogo = new TelaDeJogo(); // Instanciando a classe tela de jogo

        janela.add(telaDeJogo); // Adiciona a tela de jogo à janela que será mostrada no PC
        janela.pack(); // Faz a janela usar as configurações predefinidas
        janela.setLocationRelativeTo(null); // Abre a janela no centro da tela do PC
        janela.setVisible(true); // Permite ver a janela na tela do PC
        telaDeJogo.configGame();
        telaDeJogo.iniciaThreadJogo();
    }
}
