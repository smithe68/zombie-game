package Entities;

import Engine.*;
import Engine.Components.Visual;
import Engine.Internal.Renderer;
import Engine.Internal.Updater;

import java.awt.*;

/**
 * Created by evan on 4/29/2018.
 */
public class SmallFollowZombie extends Entity
{
    private float speed = 0.5f;

    private Hero hero;

    public void start()
    {
        visual.setRenderType(Visual.RenderType.Image);
        visual.setSprite(Renderer.getImage("ZombieSmall.png"));
        visual.setTint(Color.red);

        physics.setHasCollision(true);

        hero = (Hero)SceneManager.getEntity("Hero");

        physics.setCollisionEvent(e ->
        {
            if(e.tag.equals("Hero"))
            {
                hero.takeDamage(1f * Updater.deltaTime);
            }
        });
    }

    public void update()
    {
        if(hero != null)
        {
            var dirX = hero.transform.getX() - transform.getX();
            var dirY = hero.transform.getY() - transform.getY();

            var normal = Math.sqrt(dirX * dirX + dirY * dirY);

            if(normal > 0)
            {
                float moveX = (float)(dirX / normal) * Updater.deltaTime;
                float moveY = (float)(dirY / normal) * Updater.deltaTime;

                physics.setVelocity(moveX * speed, moveY * speed);
            }
        }
    }
}
