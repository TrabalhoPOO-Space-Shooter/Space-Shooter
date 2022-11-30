package main;

import entity.Entity;
import org.jetbrains.annotations.NotNull;

public class CollisionChecker {
    GamePanel tl;

    public CollisionChecker(GamePanel tl){
        this.tl = tl;
    }

    public void checkTile(@NotNull Entity entity)
    {
        int entityLeftX = entity.x + entity.areaSolida.x;
        int entityRightX = entity.x + entity.areaSolida.x + entity.areaSolida.width;
        int entityTopY = entity.y + entity.areaSolida.y;
        int entityBottom = entity.y + entity.areaSolida.y + entity.areaSolida.height;

        int entityLeftCol = entityLeftX/tl.tileSize;
        int entityRightCol = entityRightX/tl.tileSize;
        int entityTopRow = entityTopY/tl.tileSize;
        int entityBottomRow = entityBottom/tl.tileSize;

        int tileNum1,tileNum2;
    }
}
