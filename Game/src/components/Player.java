package components;

import engine.Component;
import engine.Entity;
import engine.Input;
import engine.components.Camera;
import engine.utility.Time;
import engine.utility.Vector;

import java.awt.*;

public class Player extends Component
{
    private ShapeRenderer shapeRenderer;
    private Physics physics;

    public Player(Entity parent)
    {
        super(parent);
        shapeRenderer = (ShapeRenderer)parent.addComponent(new ShapeRenderer(parent));
        shapeRenderer.setTint(Color.cyan);
        physics = (Physics)parent.addComponent(new Physics(parent));
        parent.layer = 1;
    }

    @Override
    protected void update()
    {
        float hori = Input.getInputAxis("Horizontal");
        float vert = Input.getInputAxis("Vertical");

        physics.velocity.set(hori, vert);

        Camera.setPosition(Vector.lerp(Camera.getPosition(), parent.transform.position,
                Time.deltaTime));
    }
}
