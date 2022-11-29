package BgImage;

import main.TelaDeJogo;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class BgImage {
    TelaDeJogo tl;
    Imagem back;
    Imagem stars;

    public BgImage(TelaDeJogo tl){
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
        g2.drawImage(stars.image,0,0, tl.larguraTela, tl.alturaTela,null); // Coloca estrelas no background
        g2.drawImage(back.image,0,0, tl.larguraTela, tl.alturaTela,null); // Altera o background para os planetas
    }

}
