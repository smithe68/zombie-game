package engine;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Scene
{
    List<Entity> entities = new LinkedList<>();

    void update()
    {
        for(int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
    }

    void render(Graphics2D g)
    {
        for(int i = 0; i < entities.size(); i++) {
            entities.get(i).render(g);
        }
    }
}
