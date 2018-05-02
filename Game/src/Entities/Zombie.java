package Entities;

import Engine.Components.Visual;
import Engine.Entity;
import Engine.Internal.Updater;

import java.awt.*;

public class Zombie extends Entity {

    private Visual.RenderType renderType = Visual.RenderType.Rectangle;
    private int angle;

    public void start()
    {
        visual.setRenderType(renderType);
        visual.setTint(Color.blue);
    }

    private float movement = 0.3f*Updater.deltaTime;

    public void update()
    {
        angle += 1;
        if(angle > 360) { angle = 0; }

        physics.setVelX((float)Math.cos(Math.toRadians(angle)));
        physics.setVelY((float)Math.sin(Math.toRadians(angle)));

    }
    public Zombie(ZombieType type){
        switch (type) {

            case Circle:
                visual.setTint(Color.green);
                renderType = Visual.RenderType.Ellipse;
                transform.setSize(16, 16);
                transform.setPos(transform.getX(), -64 + transform.getHeight() / 2);
                break;

            case Follow:

                break;
        }
    }
    public enum ZombieType
    {
        None,
        BackAndForth,
        Circle,
        Follow
    }

}
