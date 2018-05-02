package Engine.Components;

public final class Transform
{
    private float x;
    private float y;

    private float rotation;

    private float width = 32;
    private float height = 32;

    public void setPos(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public float getX() { return x; }
    public float getY() { return y; }

    public void setRot(float rotation)
    {
        this.rotation = rotation;
    }

    public float getRot() { return rotation; }

    public void setSize(float width, float height)
    {
        this.width = width;
        this.height = height;
    }

    public float getWidth() { return width; }
    public float getHeight() { return height; }

    public void setWidth(float width) { this.width = width; }
    public void setHeight(float height) { this.height = height; }
}
