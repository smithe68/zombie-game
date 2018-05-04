package io;

import engine.SceneManager;
import entities.*;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LevelReader
{
    public static void readScene(String name)
    {
        try
        {
            Scanner scanner = new Scanner(new File(
                    getLevelPath() + "/" + name));

            while(scanner.hasNext())
            {
                String line = scanner.nextLine();

                String[] object = line.split(":");
                String[] pos = object[1].split(",");

                String entityName = object[0];

                float posX = Float.parseFloat(pos[0]);
                float posY = Float.parseFloat(pos[1]);

                switch(entityName)
                {
                    case "Hero":
                        var hero = SceneManager.createEntity(new Hero());
                        hero.transform.setPos(posX, posY);
                        break;

                    case "Zombie":
                        var zombie = SceneManager.createEntity(new Zombie());
                        zombie.transform.setPos(posX, posY);
                        break;

                    case "Tile":
                        var tile = SceneManager.createEntity(new Tile());
                        tile.transform.setPos(posX, posY);
                        break;
                }
            }

            scanner.close();
        }
        catch(FileNotFoundException e) {
            System.err.println("Level [" + name + "] not Found");
        }
    }

    private static String getLevelPath()
    {
        var view = FileSystemView.getFileSystemView();
        String docs = view.getDefaultDirectory().toString();

        var folder = new File(docs + "/Zombie Game/Game/src/levels");
        if(folder.mkdirs()) { System.out.println("Created levels Folder"); }
        return docs + "/Zombie Game/Game/src/levels";
    }
}
