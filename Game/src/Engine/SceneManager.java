package Engine;

import Entities.*;

import java.awt.*;
import java.util.*;

public class SceneManager
{
    public static LinkedList<Entity> entities;

    public static void initialize()
    {
        entities = new LinkedList<>();

        SceneManager.createEntity(new Tile());

        SceneManager.createEntity(new Hero());

        var z = SceneManager.createEntity(new SmallFollowZombie());
        z.transform.setPos(100, 0);
    }

    public static void updateEntities()
    {
        for(int i = 0; i < entities.size(); i++)
        {
            entities.get(i).update();
            entities.get(i).physics.update();
        }
    }

    public static void renderEntities(Graphics2D g)
    {
        for(int i = 0; i < entities.size(); i++)
        {
            entities.get(i).visual.render(g);
            entities.get(i).fixedUpdate();
        }
    }

    public static Entity createEntity(Entity e)
    {
        entities.add(e);
        Collections.sort(entities);
        return e;
    }

    public static Entity getEntity(String tag)
    {
        for(int i = 0; i < entities.size(); i++)
        {
            if(entities.get(i).tag.equals(tag)) {
                return entities.get(i);
            }
        }

        System.err.println("Entity [" + tag + "] not Found!");
        return null;
    }

    public static void destroyEntity(Entity e)
    {
        if(e == null) { return; }
        entities.remove(e);
    }
}
