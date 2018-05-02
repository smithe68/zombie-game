package Entities;

import Engine.Entity;
import Engine.Components.Visual;

import java.awt.*;

/**
 * Created by evan on 4/29/2018.
 */
public class SmallCircleZombie extends Entity
{
    private int angle;

    public void start()
    {
        visual.setTint(Color.green);
        visual.setRenderType(Visual.RenderType.Ellipse);

        transform.setSize(16, 16);
        transform.setPos(transform.getX(), -64 + transform.getHeight() / 2);
    }

    public void update()
    {
        angle += 1;
        if(angle > 360) { angle = 0; }

        physics.setVelX((float)Math.cos(Math.toRadians(angle)));
        physics.setVelY((float)Math.sin(Math.toRadians(angle)));
    }
}
