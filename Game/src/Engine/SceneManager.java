package Engine;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Collections;
import java.util.LinkedList;

public class SceneManager
{
    static LinkedList<Entity> entities = new LinkedList<>();

    static void updateEntities()
    {
        for(int i = 0; i < entities.size(); i++)
        {
            entities.get(i).update();
            entities.get(i).collision();
            entities.get(i).physics();
        }
    }

    static void renderEntities(Graphics2D g)
    {
        for(int i = 0; i < entities.size(); i++)
        {
            entities.get(i).render(g);
            entities.get(i).fixedUpdate();
        }
    }

    public static void createEntity(Entity e)
    {
        System.out.println("Spawned " + e.getClass().getSimpleName());
        entities.add(e);
        Collections.sort(entities);
    }

    public static Entity getEntity(String tag)
    {
        for(int i = 0; i < entities.size(); i++)
        {
            if(entities.get(i).tag.equals(tag)) {
                return entities.get(i);
            }
        }

        return null;
    }

    public static void destroyEntity(Entity e)
    {
        if(e == null) { return; }
        System.out.println("Destroyed Entity" + e.getClass().getSimpleName());
        entities.remove(e);
    }
}
