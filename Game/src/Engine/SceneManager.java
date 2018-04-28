package Engine;

import java.awt.*;
import java.util.LinkedList;

public class SceneManager
{
    private static LinkedList<Entity> entities = new LinkedList<>();

    static void updateEntities()
    {
        for(int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
    }

    static void renderEntities(Graphics g)
    {
        for(int i = 0; i < entities.size(); i++) {
            entities.get(i).render(g);
        }
    }

    public static void createEntity(Entity e)
    {
        System.out.println("Spawned " + e.getClass().getSimpleName());
        entities.add(e);
    }
}
