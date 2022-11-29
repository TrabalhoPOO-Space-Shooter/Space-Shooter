package main;

import BgImage.BgImage;
import enemy.ENEMY_Spaceship;
import entity.Entity;
import entity.Jogador;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class TelaDeJogo extends JPanel implements Runnable {

    // Configurações da tela (atributos)
    final int tamOriginalQuadro = 16; // Quadro com padrão de 16x16 pixels
    final int escala = 3; // Número da escala do quadro

    public final int tamFinalQuadro = tamOriginalQuadro * escala; // Tamanho final dos quadros (48x48)
    final int tamMaxTelaCol = 16; // Tamanho máximo de colunas na tela
    final int tamMaxTelaLin = 12; // Tamanho máximo de linhas na tela
    public final int larguraTela = tamFinalQuadro * tamMaxTelaCol; // Largura da tela (768 pixels)
    public final int alturaTela = tamFinalQuadro * tamMaxTelaLin; // Altura da tela (576 pixels)

    final int fps = 60; // Frames por segundo que a tela será atualizada

    Controle controle = new Controle(this);
    Thread threadJogo; // Atualiza a tela 60 vezes por segundo (60 fps)
    public Jogador jogador = new Jogador(this, controle);

    // Collision checker

    public CollisionChecker cChecker = new CollisionChecker(this);

    // Set asset

    AssetSetter aSetter = new AssetSetter(this);

    // Enemy
    public Entity enemy[] = new Entity[10];

    // Background image
    BgImage bgA = new BgImage(this);

    // UI

    public UI ui = new UI(this);

    // Game Sound
    Sound sound = new Sound();

    // Game State
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int titleState = 0;

    // ENTIDADE E OBJETOS
    ArrayList<Entity> listaDeProjeteis = new ArrayList<>();

    // Construtor
    public TelaDeJogo() {

        this.setPreferredSize(new Dimension(larguraTela, alturaTela)); // Põe as dimensões na tela
        this.setBackground(Color.black); // Põe uma cor de fundo na tela
        this.setDoubleBuffered(true); // Melhora a performance do render
        this.addKeyListener(controle); // Recebe a tecla pressionada
        this.setFocusable(true);
    }

    public void configGame(){
        aSetter.setEnemy();
        gameState = titleState;
        playMusic(0); // Toca a música que está na pasta res/sound
    }

    public void iniciaThreadJogo() {

        threadJogo = new Thread(this); // Passa a TelaDeJogo para a thread
        threadJogo.start(); // Inicia a thread
    }

    // Método run (Sempre que a threadJogo for utilizada o método run é chamado)
    @Override
    public void run() { // Utilizada para fazer o looping do jogo

        double intervalo = 1000000000 / (double) fps; // 0.016666 segundos
        double proximoIntervalo = System.nanoTime() + intervalo;

        while (threadJogo != null) { // Enquanto a thread existir o loop repete

            update(); // Atualiza a informação
            repaint(); // Atualiza a tela (Chamada do método paintComponent)

            try {
                double tempoDeSobra = (proximoIntervalo - System.nanoTime()) / 1000000;

                if (tempoDeSobra < 0) {
                    tempoDeSobra = 0;
                }

                Thread.sleep((long) tempoDeSobra);

                proximoIntervalo += intervalo;

            } catch (InterruptedException controle) {
                controle.printStackTrace();
            }
        }

    }

    public void update() { // Atualiza a informação
        if(gameState == playState){
            jogador.update(); // Atualiza a informação do jogador na tela
        }
        if(gameState == pauseState){
             //Nothing
        }

    }

    public void paintComponent(Graphics g) { // Pinta a tela (Subclasse de JPanel)
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // Graphics2D é uma extensão da Graphics

        // Tela inicial com as opções de new game, etc...
        if(gameState == titleState){
            ui.draw(g2);
        }else{
            bgA.draw(g2); // Desenha o background
            // Enemy
            for(int i=0; i < enemy.length; i++){
                if(enemy[i] != null){
                    enemy[i].draw(g2);
                }
            }
            jogador.draw(g2); // Atualiza o desenho do jogador na tela
            ui.draw(g2);
            g2.dispose();
        }

    }

    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic(){
        sound.stop();
    }

    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }
}
