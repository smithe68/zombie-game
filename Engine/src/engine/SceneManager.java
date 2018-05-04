package engine;

import java.awt.*;
import java.util.*;

public class SceneManager
{
    public static Scene currentScene = new Scene();

    public static void updateEntities()
    {
        for(int i = 0; i < currentScene.entities.size(); i++)
        {
            currentScene.entities.get(i).update();
            currentScene.entities.get(i).physics.update();
        }
    }

    public static void renderEntities(Graphics2D g)
    {
        for(int i = 0; i < currentScene.entities.size(); i++)
        {
            currentScene.entities.get(i).visual.render(g);
            currentScene.entities.get(i).fixedUpdate();
        }
    }

    public static Entity createEntity(Entity e)
    {
        currentScene.entities.add(e);
        Collections.sort(currentScene.entities);
        return e;
    }

    public static Entity getEntity(String tag)
    {
        for(int i = 0; i < currentScene.entities.size(); i++)
        {
            if(currentScene.entities.get(i).tag.equals(tag)) {
                return currentScene.entities.get(i);
            }
        }

        System.err.println("Entity [" + tag + "] not Found!");
        return null;
    }

    public static void destroyEntity(Entity e)
    {
        if(e == null) { return; }
        currentScene.entities.remove(e);
    }
}
