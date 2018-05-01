package Engine;

import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;

public class SceneManager
{
    static LinkedList<Entity> entities = new LinkedList<>();
    private static LinkedList<Entity> entitiesToSpawn = new LinkedList<>();

    static void updateEntities()
    {
        for(int i = 0; i < entities.size(); i++)
        {
            entities.get(i).update();
            entities.get(i).physics.update();
        }

        for(int i = entitiesToSpawn.size() - 1; i >= 0; i--)
        {
            entities.add(entitiesToSpawn.get(i));
            entitiesToSpawn.remove(i);
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
        entitiesToSpawn.add(e);
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
