package engine;

import engine.components.Transform;

import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

public final class Entity implements Comparable<Entity>
{
    public int layer;
    public String name;
    public Transform transform;

    private List<Component> components;

    public Entity(String name)
    {
        this.name = name;
        components = new LinkedList<>();
        transform = new Transform(this);
        components.add(transform);
    }

    void update()
    {
        for(int i = 0; i < components.size(); i++) {
            components.get(i).update();
        }
    }

    void render(Graphics2D g)
    {
        for(int i = 0; i < components.size(); i++) {
            components.get(i).render(g);
        }
    }

    public Component addComponent(Component component)
    {
        components.add(component);
        return component;
    }

    public <T extends Component> Component getComponent(Class<T> clazz)
    {
        for(int i = 0; i < components.size(); i++)
        {
            if(components.get(i).getClass().isAssignableFrom(clazz)) {
                return components.get(i);
            }
        }

        return null;
    }

    public void removeComponent(Component component) {
        components.remove(component);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public int compareTo(Entity o) {
        return layer - o.layer;
    }
}