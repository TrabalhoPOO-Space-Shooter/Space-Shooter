package object;

import entity.Projetil;
import main.GamePanel;

public class OBJ_Laser extends Projetil {
    GamePanel tl;

    public OBJ_Laser(GamePanel tl){
        super(tl,30,30);
        this.tl = tl;
        nome = "Laser";
        speed = 5;
        ataque = 2;
        pegaImagem();

    }

    public void pegaImagem(){
        image = setup("../res/assetsTeste/laser");
    }
}
