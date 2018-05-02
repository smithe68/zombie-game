package Engine;

import Entities.*;

import java.awt.*;
import java.util.*;

public class SceneManager
{
    static LinkedList<Entity> entities;

    private static Queue<Entity> spawnQueue;

    static void initialize()
    {
        entities = new LinkedList<>();
        spawnQueue = new LinkedList<>();

        SceneManager.createEntity(new Hero());
        SceneManager.createEntity(new Tile());
    }

    static void updateEntities()
    {
        for(int i = 0; i < entities.size(); i++)
        {
            entities.get(i).update();
            entities.get(i).physics.update();
        }

        for(int i = 0; i < spawnQueue.size(); i++)
        {
            entities.add(spawnQueue.remove());
            Collections.sort(entities);
        }
    }

    static void renderEntities(Graphics2D g)
    {
        for(int i = 0; i < entities.size(); i++) {
            entities.get(i).visual.render(g);
        }
    }

    public static Entity createEntity(Entity e)
    {
        spawnQueue.add(e);
        return e;
    }

    public static Entity getEntity(String tag)
    {
        try
        {
            for(int i = 0; i < entities.size(); i++)
            {
                if(entities.get(i).tag.equals(tag)) {
                    return entities.get(i);
                }
            }
        }
        catch(NullPointerException e) {
            System.err.println("Entity [" + tag + "] not Found!");
        }

        return null;
    }

    public static void destroyEntity(Entity e)
    {
        if(e == null) { return; }
        entities.remove(e);
    }
}
