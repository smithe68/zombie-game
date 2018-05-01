package Entities;

import Engine.Entity;
import Engine.Mafs;
import Engine.SceneManager;
import Engine.Updater;

import java.awt.*;

public class Bullet extends Entity
{
    private Hero hero;

    private float initialVelX;
    private float initialVelY;

    public void start()
    {
        renderType = RenderType.Rectangle;
        renderTint = Color.WHITE;

        width = 6;
        height = 6;

        hero = (Hero)SceneManager.getEntity("Hero");

        initialVelX = (float)Math.cos(hero.rotation);
        initialVelY = (float)Math.sin(hero.rotation);
    }
    public void update()
    {
        velX = initialVelX * Updater.deltaTime;
        velY = initialVelY * Updater.deltaTime;

        if(velX>=100){
            SceneManager.destroyEntity(this);

        }
        if(velY>=100){
            SceneManager.destroyEntity(this);
        }
    }
}
