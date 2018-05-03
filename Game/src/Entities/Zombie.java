package Entities;
//
import Engine.Components.Visual;
import Engine.Entity;
import Engine.Internal.*;
import Engine.SceneManager;

public class Zombie extends Entity
{
    private ZombieType type;

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
        visual.setSprite(Renderer.getImage("ZombieSmall.png"));
        hero = (Hero)SceneManager.getEntity("Hero");

        visual.setLayer(2);

        physics.setHasCollision(true);

        physics.setCollisionEvent(e ->
        {
            if(e.tag.equals("Hero")) {
                hero.takeDamage(Updater.deltaTime);
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
                float moveX = (float)(dirX / normal) * Updater.deltaTime;
                float moveY = (float)(dirY / normal) * Updater.deltaTime;

                physics.setVelocity(moveX * speed, moveY * speed);
            }
        }
    }

    public void setType(ZombieType type) { this.type = type; }
}
