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
    private SpriteRenderer spriteRenderer;
    private Physics physics;

    public Player(Entity parent)
    {
        super(parent);
        spriteRenderer = (SpriteRenderer)parent.addComponent(new SpriteRenderer(parent));
        spriteRenderer.setTint(Color.cyan);
        spriteRenderer.setSprite("Hero.png");
        physics = (Physics)parent.addComponent(new Physics(parent));
        parent.layer = 1;
    }

    @Override
    protected void update()
    {
        float hori = Input.getInputAxis("Horizontal");
        float vert = Input.getInputAxis("Vertical");

        physics.velocity.set(hori, vert);

        Camera.setPosition(Vector.lerp(Camera.getPosition(), transform.position,
                Time.deltaTime));

        float mouseX = Input.getRelativeMouseX();
        float mouseY = Input.getRelativeMouseY();

        transform.setRotation((float)Math.toDegrees(Math.atan2(mouseY, mouseX)));
    }
}
