package Engine;

public final class Transform
{
    private float x;
    private float y;

    private float rotation;

    private int width = 32;
    private int height = 32;

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

    public void setSize(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
