package main;

import entity.Entity;
import org.jetbrains.annotations.NotNull;

public class CollisionChecker {
    TelaDeJogo tl;

    public CollisionChecker(TelaDeJogo tl){
        this.tl = tl;
    }

    public void checkTile(@NotNull Entity entity)
    {
        int entityLeftX = entity.x + entity.areaSolida.x;
        int entityRightX = entity.x + entity.areaSolida.x + entity.areaSolida.width;
        int entityTopY = entity.y + entity.areaSolida.y;
        int entityBottom = entity.y + entity.areaSolida.y + entity.areaSolida.height;

        int entityLeftCol = entityLeftX/tl.tamFinalQuadro;
        int entityRightCol = entityRightX/tl.tamFinalQuadro;
        int entityTopRow = entityTopY/tl.tamFinalQuadro;
        int entityBottomRow = entityBottom/tl.tamFinalQuadro;

        int tileNum1,tileNum2;
    }
}
