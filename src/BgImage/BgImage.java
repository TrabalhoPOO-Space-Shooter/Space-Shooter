package BgImage;

import main.TelaDeJogo;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class BgImage {
    TelaDeJogo tl;
    Imagem back;

    public BgImage(TelaDeJogo tl){
        this.tl = tl;
        back = new Imagem();
        pegaBgImage();

    }

    public void pegaBgImage(){
        try{
            back = new Imagem();
            back.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("../res/spaceBg/spaceBg.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2)
    {
        g2.drawImage(back.image,0,0,null);
    }

}
