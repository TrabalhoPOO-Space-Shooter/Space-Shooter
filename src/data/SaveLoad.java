package data;

import main.GamePanel;

import java.io.*;

public class SaveLoad {
    GamePanel gp;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
    }

    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

            DataStorage ds = new DataStorage();

            ds.enemySize = gp.enemySize;
            ds.enemy = gp.enemy;
            ds.speed = gp.player.speed;

            //Write the DataStorage object
            oos.writeObject(ds);

        } catch (Exception e) {
            System.out.println("Save Exception!");
        }
    }

    public void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            //Read the DataStorage object
            DataStorage ds = (DataStorage) ois.readObject();

            gp.enemySize = ds.enemySize;
            gp.enemy = ds.enemy;
            gp.player.speed = ds.speed;

        } catch (Exception e) {
            System.out.println("Load Exception!");
        }
    }
}
