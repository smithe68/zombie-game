package engine.utility;

/**
 * Represents and Holds a Position or
 * Direction in Space. Also has handy
 * Functions.
 * @author Jakub
 */
public class Vector
{
    private float x;
    private float y;

    public Vector() { this(0, 0); }

    public Vector(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public void set(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public void set(Vector vector)
    {
        this.x = vector.x;
        this.y = vector.y;
    }

    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }

    public float getX() { return x; }
    public float getY() { return y; }

    public void add(Vector vec)
    {
        x += vec.x;
        y += vec.y;
    }

    public void add(float x, float y)
    {
        this.x += x;
        this.y += y;
    }

    public void sub(Vector vec)
    {
        x -= vec.x;
        y -= vec.y;
    }

    public void sub(float x, float y)
    {
        this.x -= x;
        this.y -= y;
    }

    public void mul(Vector vec)
    {
        x *= vec.x;
        y *= vec.y;
    }

    public void mul(float x, float y)
    {
        this.x *= x;
        this.y *= y;
    }

    public float getMagnitude() {
        return (float)Math.sqrt(x * x + y * y);
    }

    public void normalize()
    {
        x /= (float)Math.sqrt(x * x + y * y);
        y /= (float)Math.sqrt(x * x + y * y);
    }

    public static Vector getDirection(Vector start, Vector end) {
        return new Vector(end.x - start.x, end.y - start.y);
    }

    public static Vector lerp(Vector a, Vector b, float t) {
        return new Vector(Mafs.lerp(a.x, b.x, t), Mafs.lerp(a.y, b.y, t));
    }
}
