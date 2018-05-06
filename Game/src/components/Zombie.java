package components;

import engine.Component;
import engine.Entity;
import engine.SceneManager;
import engine.utility.Vector;

import java.awt.*;

public class Zombie extends Component
{
    private Entity player;
    private SpriteRenderer spriteRenderer;
    private Physics physics;

    public Zombie(Entity parent)
    {
        super(parent);
        spriteRenderer = (SpriteRenderer)addComponent(new SpriteRenderer(parent));
        spriteRenderer.setSprite("ZombieSmall.png");
        spriteRenderer.setTint(Color.red);

        physics = (Physics)addComponent(new Physics(parent));
        player = SceneManager.getEntity("Player");
    }

    @Override
    protected void update(float delta)
    {
        followPlayer(delta);
        transform.lookAt(player.transform);
    }

    private void followPlayer(float delta)
    {
        if(player == null) { return; }

        var dir = Vector.getDirection(transform.position,
                player.transform.position);

        dir.normalize();
        dir.mul(delta, delta);

        physics.velocity.set(dir);
    }
}
