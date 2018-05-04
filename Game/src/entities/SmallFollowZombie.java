package entities;

import engine.*;
import engine.components.Visual;
import engine.SceneManager;

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
        visual.setSprite("ZombieSmall.png");
        visual.setTint(Color.red);

        physics.setHasCollision(true);

        hero = (Hero)SceneManager.getEntity("Hero");

        physics.setCollisionEvent(e ->
        {
            if(e.tag.equals("Hero"))
            {
                hero.takeDamage(1f * Time.deltaTime);
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
                float moveX = (float)(dirX / normal) * Time.deltaTime;
                float moveY = (float)(dirY / normal) * Time.deltaTime;

                physics.setVelocity(moveX * speed, moveY * speed);
            }
        }
    }
}
