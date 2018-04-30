package Entities;

import Engine.Entity;
import Engine.Mafs;
import Engine.SceneManager;
import Engine.Updater;

import java.awt.*;

public class Bullet extends Entity{
    private Hero hero;
    private float originX=hero.x;
    private float originY = hero.y;

    public void start()
    {
        renderType = RenderType.Rectangle;
        renderTint = Color.WHITE;

        width = 6;
        height = 6;
        hero = (Hero)SceneManager.getEntity("Hero");
    }
    public void update()
    {
        velX = (originX+1)*Updater.deltaTime;
        //velY -= (originY+1)*Updater.deltaTime;
        if(velX>=100){
            SceneManager.destroyEntity(this);

        };
        if(velY>=100){
            SceneManager.destroyEntity(this);
        }
    }
}
