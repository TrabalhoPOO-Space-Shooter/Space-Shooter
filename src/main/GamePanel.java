package main;

import BgImage.BgImage;
import entity.Entity;
import entity.Player;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    // Configurações da tela (atributos)
    final int originalTileSize = 16; // Quadro com padrão de 16x16 pixels
    final int scale = 3; // Número da escala do quadro

    public final int tileSize = originalTileSize * scale; // Tamanho final dos quadros (48x48)
    final int maxScreenCol = 16; // Tamanho máximo de colunas na tela
    final int maxScreenRow = 12; // Tamanho máximo de linhas na tela
    public final int screenWidth = tileSize * maxScreenCol; // Largura da tela (768 pixels)
    public final int screenHeight = tileSize * maxScreenRow; // Altura da tela (576 pixels)

    final int FPS = 60; // Frames por segundo que a tela será atualizada

    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread; // Atualiza a tela 60 vezes por segundo (60 fps)
    public Player player = new Player(this, keyH);

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
    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Põe as dimensões na tela
        this.setBackground(Color.black); // Põe uma cor de fundo na tela
        this.setDoubleBuffered(true); // Melhora a performance do render
        this.addKeyListener(keyH); // Recebe a tecla pressionada
        this.setFocusable(true);
    }

    public void configGame(){
        aSetter.setEnemy();
        gameState = titleState;
        playMusic(0); // Toca a música que está na pasta res/sound
    }

    public void startGameThread() {

        gameThread = new Thread(this); // Passa a TelaDeJogo para a thread
        gameThread.start(); // Inicia a thread
    }

    // Método run (Sempre que a threadJogo for utilizada o método run é chamado)
    @Override
    public void run() { // Utilizada para fazer o looping do jogo

        double drawInterval = 1000000000 / (double) FPS; // 0.016666 segundos
        double nextDrawInterval = System.nanoTime() + drawInterval;

        while (gameThread != null) { // Enquanto a thread existir o loop repete

            update(); // Atualiza a informação
            repaint(); // Atualiza a tela (Chamada do método paintComponent)

            try {
                double remainingTime = (nextDrawInterval - System.nanoTime()) / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawInterval += drawInterval;

            } catch (InterruptedException controle) {
                controle.printStackTrace();
            }
        }

    }

    public void update() { // Atualiza a informação
        if(gameState == playState){
            sound.play(); // Toca o som
            player.update(); // Atualiza a informação do jogador na tela
        }
        if(gameState == pauseState){
             sound.stop(); // Para o som
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
            player.draw(g2); // Atualiza o desenho do jogador na tela
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
