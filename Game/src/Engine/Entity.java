package Engine;

public class Entity implements Comparable<Entity>
{
    public String tag;
    public Transform transform;
    public Physics physics;
    public Visual visual;

    public Entity()
    {
        tag = getClass().getSimpleName();

        transform = new Transform();
        physics = new Physics(transform);
        visual = new Visual(transform);

        start();
    }

    public void start() {}
    public void update() {}

    @Override
    public int compareTo(Entity o) {
        return visual.getLayer() - o.visual.getLayer();
    }
}