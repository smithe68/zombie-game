package components;

import engine.*;
import engine.utility.Time;
import engine.utility.Vector;
import enums.Shapes;

import java.awt.Color;

public class Zombie extends Component
{
    private Entity player;
    private ShapeRenderer shapeRenderer;
    private Physics physics;

    public Zombie(Entity parent)
    {
        super(parent);
        shapeRenderer = (ShapeRenderer)parent.addComponent(new ShapeRenderer(parent));
        shapeRenderer.setShape(Shapes.Ellipse);
        shapeRenderer.setTint(Color.red);

        physics = (Physics)parent.addComponent(new Physics(parent));
        player = SceneManager.getEntity("Player");
    }

    @Override
    protected void update()
    {
        if(player != null)
        {
            var dir = Vector.getDirection(parent.transform.position,
                    player.transform.position);
            dir.normalize();

            dir.mul(Time.deltaTime, Time.deltaTime);
            physics.velocity.set(dir);
        }
    }
}
