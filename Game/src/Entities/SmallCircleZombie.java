package Entities;

import Engine.Entity;
import Engine.Input;
import Engine.Updater;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by evan on 4/29/2018.
 */
public class SmallCircleZombie extends Entity
{
    private int angle;

    public void start()
    {
        renderTint = Color.green;
        renderType = RenderType.EllipseBorder;

        width = 16;
        height = 16;

        y = -64 + height / 2;
    }

    public void fixedUpdate()
    {
        angle += 1;
        if(angle > 360) { angle = 0; }

        velX = (float)Math.cos(Math.toRadians(angle));
        velY = (float)Math.sin(Math.toRadians(angle));
    }
}
