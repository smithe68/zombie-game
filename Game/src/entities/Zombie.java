package entities;

import engine.*;
import engine.components.Visual;
import engine.SceneManager;

public class Zombie extends Entity
{
    private ZombieType type = ZombieType.Follow;

    private int angle;
    private float speed = 0.5f;
    private Hero hero;

    public enum ZombieType
    {
        Follow
    }

    public void start()
    {
        visual.setRenderType(Visual.RenderType.Image);
        visual.setSprite("ZombieSmall.png");
        hero = (Hero)SceneManager.getEntity("Hero");

        visual.setLayer(2);

        physics.setHasCollision(true);

        physics.setCollisionEvent(e ->
        {
            if(e.tag.equals("Hero")) {
                hero.takeDamage(Time.deltaTime);
            }
        });
    }

    public void update()
    {
        switch(type)
        {
            case Follow:
                followHero();
                break;
        }
    }

    private void followHero()
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

    public void setType(ZombieType type) { this.type = type; }
}
