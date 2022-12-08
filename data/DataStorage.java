package data;

import enemy.ENEMY_Spaceship;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable {
    // Player Stats
    int enemySize;
    int speed;
    ArrayList<ENEMY_Spaceship> enemy;

}
