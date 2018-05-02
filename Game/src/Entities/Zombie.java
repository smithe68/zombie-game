package Entities;
//
import Engine.Components.Transform;
import Engine.Components.Visual;
import Engine.Entity;
import Engine.Internal.Renderer;
import Engine.Internal.Updater;
import Engine.SceneManager;

import java.awt.*;

public class Zombie extends Entity {

    private int angle;

    private Color color;
    Visual.RenderType renderType;

    private float xMov;
    private float yMov;
    private float speed = 0.5f;

    private int width;
    private int heigh;

    private Hero hero;

    private boolean isFollow= false;


    public Zombie(ZombieType type){
        switch (type) {

            case Circle:
                color = Color.green;
                renderType = Visual.RenderType.Ellipse;
                heigh = 16;
                width = 16;
                xMov = (float)Math.cos(Math.toRadians(angle));
                yMov = (float)Math.sin(Math.toRadians(angle));
                break;

            case Follow:

                visual.setSprite(Renderer.getImage("ZombieSmall.png"));
                color = Color.red;
                isFollow = true;
                break;
        }
    }
    public void start()
    {
        visual.setTint(color);
        visual.setRenderType(renderType);
        transform.setSize(width, heigh);
        transform.setPos(transform.getX(), -64 + transform.getHeight() / 2);



        hero = (Hero)SceneManager.getEntity("Hero")
        ;

        physics.setHasCollision(true);
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
        angle += 1;
        if(angle > 360) { angle = 0; }
        if (isFollow){
            if(hero != null) {
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


        physics.setVelX(xMov);
        physics.setVelY(yMov);

    }
    public enum ZombieType
    {
        None,
        BackAndForth,
        Circle,
        Follow
    }

}
