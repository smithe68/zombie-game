package Entities;

import Engine.*;

import java.awt.*;

public class Bullet extends Entity
{
    private Hero hero;

    private float initialVelX;
    private float initialVelY;

    public void start()
    {
        hero = (Hero)SceneManager.getEntity("Hero");

        visual.setRenderType(Visual.RenderType.Rectangle);
        visual.setTint(Color.WHITE);

        transform.setSize(6, 6);
        transform.setPos(hero.transform.getX(), hero.transform.getY());

        initialVelX = (float)Math.cos(transform.getRot() * transform.getX());
        initialVelY = -(float)Math.sin(transform.getRot() * -transform.getY());
    }

    public void update()
    {
        physics.setVelX(initialVelX * Updater.deltaTime);
        physics.setVelY(initialVelY * Updater.deltaTime);

        if(physics.getVelX() >= 100){
            SceneManager.destroyEntity(this);
        }

        if(physics.getVelY() >= 100){
            SceneManager.destroyEntity(this);
        }
    }
}
