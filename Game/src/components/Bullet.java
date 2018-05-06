package components;

import engine.Component;
import engine.Entity;
import engine.SceneManager;
import engine.utility.Vector;

import java.awt.Color;

public class Bullet extends Component
{
    public float speed = 5;

    private Vector initialVelocity;
    private ShapeRenderer shapeRenderer;
    private Physics physics;

    private int time;

    public Bullet(Entity parent)
    {
        super(parent);
        shapeRenderer = (ShapeRenderer)parent.addComponent(new ShapeRenderer(parent));
        shapeRenderer.setTint(Color.yellow);

        transform.setSize(6, 6);
        physics = (Physics)parent.addComponent(new Physics(parent));
        parent.layer = 2;
    }

    @Override
    protected void update(float delta)
    {
        physics.velocity.set(initialVelocity);

        if(++time * delta > 60) {
            SceneManager.destroyEntity(parent);
        }
    }

    public void setInitialVelocity(Vector vel) {
        initialVelocity = new Vector(vel.getX() * speed, vel.getY() * speed);
    }
}
