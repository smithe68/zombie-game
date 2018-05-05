package engine.components;

import engine.Component;
import engine.Entity;
import engine.utility.Vector;

public class Camera extends Component
{
    private static Vector position = new Vector();

    public Camera(Entity parent) {
        super(parent);
    }

    public static void setPosition(Vector position) {
        Camera.position = position;
    }

    public static Vector getPosition() {
        return Camera.position;
    }
}
