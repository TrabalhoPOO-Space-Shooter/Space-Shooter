package BgImage;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class BgImage {
    GamePanel tl;
    private int y;
    Imagem back;
    Imagem stars;

    public BgImage(GamePanel tl) {
        this.tl = tl;
        back = new Imagem();
        pegaBgImage();
        y = 0;
    }

    public void pegaBgImage() {
        try {
            back = new Imagem();
            stars = new Imagem();
            back.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("../res/assetsTeste/background.png")));
            stars.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("../res/assetsTeste/background_stars.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        // Fazer a troca ficar suave
        g2.drawImage(stars.image, 0, y - 576 * 2, tl.screenWidth, tl.screenHeight, null); // Coloca estrelas no background
        g2.drawImage(back.image, 0, y - 576 * 2, tl.screenWidth, tl.screenHeight, null); // Altera o background para os planetas

        // Gambiarra
        g2.drawImage(stars.image, 0, y, tl.screenWidth, -tl.screenHeight, null);
        g2.drawImage(back.image, 0, y, tl.screenWidth, -tl.screenHeight, null);
        // Fundo
        g2.drawImage(stars.image, 0, y, tl.screenWidth, tl.screenHeight, null); // Coloca estrelas no background
        g2.drawImage(back.image, 0, y, tl.screenWidth, tl.screenHeight, null); // Altera o background para os planetas
        y += 3;
        if (y > 576 * 2) {
            y = 0;
        }
    }

}
