package object;

import entity.Projetil;
import main.TelaDeJogo;

public class OBJ_Laser extends Projetil {
    TelaDeJogo tl;

    public OBJ_Laser(TelaDeJogo tl){
        super(tl);
        this.tl = tl;
        nome = "Laser";
        velocidade = 5;
        ataque = 2;
        pegaImagem();

    }

    public void pegaImagem(){
        imagem = setup("../res/assetsTeste/laser");
    }
}
