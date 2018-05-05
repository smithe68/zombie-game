package engine.components;

import engine.Component;
import engine.Entity;
import engine.utility.Vector;

/**
 * Represents a Entity's Position, Rotation,
 * Width, and Height relative to the World.
 * (All Entity's must have this Component)
 * @author Jakub
 */
public final class Transform extends Component
{
    public Vector position = new Vector();

    private float rotation = 0;

    private float height = 32;
    private float width = 32;

    public Transform(Entity parent) {
        super(parent);
    }

    public void setRotation(float rot) { rotation = rot; }
    public float getRotation() { return rotation; }

    public void setWidth(float w) { width = w; }
    public float getWidth() { return width; }

    public void setHeight(float h) { height = h; }
    public float getHeight() { return height; }

    public void setSize(float width, float height)
    {
        this.width = width;
        this.height = height;
    }
}
