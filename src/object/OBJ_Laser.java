package object;

import entity.Projetil;
import main.GamePanel;

public class OBJ_Laser extends Projetil {
    GamePanel gp;

    public OBJ_Laser(GamePanel gp) {
        super(gp, 30, 30);
        this.gp = gp;
        nome = "Laser";
        speed = 5;
        ataque = 2;
        pegaImagem();

    }

    public void pegaImagem() {
        image = setup("../res/assetsTeste/laser");
    }
}
