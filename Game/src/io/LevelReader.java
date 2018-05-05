package io;

import engine.Component;
import engine.Entity;
import engine.SceneManager;
import engine.components.Camera;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
                String[] info = object[1].split(",");

                String entityName = object[0];

                float posX = Float.parseFloat(info[0]);
                float posY = Float.parseFloat(info[1]);

                float width = Float.parseFloat(info[2]);
                float height = Float.parseFloat(info[3]);
                int layer = Integer.parseInt(info[4]);

                var ent = SceneManager.createEntity(entityName);

                ent.transform.position.set(posX, posY);
                ent.transform.setSize(width, height);
                ent.layer = layer;

                if(object.length == 2) { continue; }

                for(int i = 2; i < object.length; i++)
                {
                    if(object[i].equals("Camera"))
                    {
                        ent.addComponent(new Camera(ent));
                        continue;
                    }

                    try
                    {
                        Class<?> clazz = Class.forName("components." + object[i]);
                        Constructor<?> ctor = clazz.getConstructor(Entity.class);
                        ent.addComponent((Component)ctor.newInstance(ent));
                    }
                    catch(ClassNotFoundException c) {
                        System.err.println("Component [" + object[i] + "] does not exist!");
                    }
                    catch(NoSuchMethodException | IllegalAccessException |
                            InstantiationException | InvocationTargetException n) { n.printStackTrace(); }
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
