package main;

import BgImage.BgImage;
import enemy.ENEMY_Spaceship;
import entity.Entity;
import entity.Explosions;
import entity.Player;
import entity.Projetil;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

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

    public KeyHandler keyH = new KeyHandler(this);
    Thread gameThread; // Atualiza a tela 60 vezes por segundo (60 fps)
    public Player player = new Player(this, keyH);

    // Set asset

    AssetSetter aSetter = new AssetSetter(this);

    // Explosions
    private ArrayList<Explosions> explosions;

    // Enemy
    private ArrayList<ENEMY_Spaceship> enemy;


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

    public final int gameWin = 4;

    public final int gameOverState = 6;

    // ENTIDADE E OBJETOS
    ArrayList<Entity> entityList = new ArrayList<>();

    // Construtor
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Põe as dimensões na tela
        this.setBackground(Color.black); // Põe uma cor de fundo na tela
        this.setDoubleBuffered(true); // Melhora a performance do render
        this.addKeyListener(keyH); // Recebe a tecla pressionada
        this.setFocusable(true);
        enemy = new ArrayList<ENEMY_Spaceship>();
        explosions = new ArrayList<Explosions>();
        for(int i=0; i<60; i++){
            enemy.add(new ENEMY_Spaceship(this,50 + i % 10 * 50,50 + i/10 * 50,1));
        }
    }

    public void configGame(){
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
            // Jogador
            player.update(); // Atualiza a informação do jogador na tela

            if(enemy.size() == 0){
                gameState = gameWin;
            }

            // Atualiza o inimigo
            for(int i=0; i<enemy.size(); i++) {
                enemy.get(i).update();
                if(enemy.get(i).getY() >= 576 - 120){
                    gameState = gameOverState;
                }
            }

            // Faz o inimigo mudar de direção quando bate na borda e aumenta a velocidade dos mesmos.

            for(int i=0; i<enemy.size(); i++) {
                if(enemy.get(i).getX() < 0 || enemy.get(i).getX() >= 768 - 50){
                    for(int j=0; j<enemy.size(); j++) {
                        enemy.get(j).swapDirection();
                    }
                    break;
                }
            }

            for(int i=0; i<explosions.size(); i++) {
                explosions.get(i).update();
                if(explosions.get(i).explosionEnds()){
                    explosions.remove(i);
                }
            }


            // Atualiza os tiros
            for(int i=0; i<player.tiros.size(); i++){
                player.tiros.get(i).update();
                if(player.tiros.get(i).destroy()) // Destroi os tiros que já passaram do tamanho da tela.
                {
                    player.tiros.remove(i);
                    i--;
                }else {
                    for (int j = 0; j < enemy.size(); j++) {
                        if (player.tiros.get(i).collideWith(enemy.get(j))) {
                            explosions.add(new Explosions(this,enemy.get(j).getX(),enemy.get(j).getY()));

                            enemy.remove(j);
                            j--;
                            //player.tiros.remove(i);
                            break;
                        }
                    }
                }
            }
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

            // Desenha as explosões
            for(int i=0; i<explosions.size(); i++) {
                explosions.get(i).draw(g2);
            }

            // Desenha os tiros
            for(int i=0; i<player.tiros.size(); i++){
                player.tiros.get(i).draw(g2);
            }

            // Desenha o inimigo
            for(int i=0; i<enemy.size(); i++) {
                enemy.get(i).draw(g2);
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
