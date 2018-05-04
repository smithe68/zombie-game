package engine;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Scene
{
    public List<Entity> entities = new LinkedList<>();

    public void update()
    {
        for(int i = 0; i < entities.size(); i++)
        {
            entities.get(i).update();
            entities.get(i).physics.update();
        }
    }

    public void render(Graphics2D g)
    {
        for(int i = 0; i < entities.size(); i++)
        {
            entities.get(i).visual.render(g);
            entities.get(i).fixedUpdate();
        }
    }
}
