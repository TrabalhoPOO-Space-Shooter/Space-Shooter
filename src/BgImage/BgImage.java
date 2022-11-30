package BgImage;

import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class BgImage {
    GamePanel tl;
    Imagem back;
    Imagem stars;

    public BgImage(GamePanel tl){
        this.tl = tl;
        back = new Imagem();
        pegaBgImage();

    }

    public void pegaBgImage(){
        try{
            back = new Imagem();
            stars = new Imagem();
            back.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("../res/assetsTeste/background.png")));
            stars.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("../res/assetsTeste/background_stars.png")));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2)
    {
        g2.drawImage(stars.image,0,0, tl.screenWidth, tl.screenHeight,null); // Coloca estrelas no background
        g2.drawImage(back.image,0,0, tl.screenWidth, tl.screenHeight,null); // Altera o background para os planetas
    }

}
