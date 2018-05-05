package components;

import engine.*;
import engine.utility.Time;
import engine.utility.Vector;

import java.awt.Color;

public class Zombie extends Component
{
    private Entity player;
    private SpriteRenderer spriteRenderer;
    private Physics physics;

    public Zombie(Entity parent)
    {
        super(parent);
        spriteRenderer = (SpriteRenderer)parent.addComponent(new SpriteRenderer(parent));
        spriteRenderer.setSprite("ZombieSmall.png");
        spriteRenderer.setTint(Color.red);

        physics = (Physics)parent.addComponent(new Physics(parent));
        player = SceneManager.getEntity("Player");
    }

    @Override
    protected void update()
    {
        if(player != null)
        {
            var dir = Vector.getDirection(transform.position,
                    player.transform.position);
            dir.normalize();

            dir.mul(Time.deltaTime, Time.deltaTime);
            physics.velocity.set(dir);
            spriteRenderer.lookAt(player.transform);
        }
    }
}
