package Engine.Utility;

public class Mafs
{
    public static float lerp (float a, float b, float t) {
        return a + t * (b - a);
    }

    public static float clamp(float val, float min, float max)
    {
        if(val > max) { return max; }
        if(val < min) { return min; }
        return val;
    }
}
