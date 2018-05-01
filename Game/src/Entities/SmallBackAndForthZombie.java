package Entities;

import Engine.Entity;
import Engine.Renderer;
import Engine.Updater;

import java.awt.*;

/**
 * Created by evan on 4/29/2018.
 */
public class SmallBackAndForthZombie extends Entity{

    public void start()
    {
        renderType = RenderType.RectangleBorder;
        renderTint = Color.blue;
    }

    private float movement = 0.3f;

    public void update()
    {
        velX = movement;
        //velY = movement;
        if (x>=50){movement = -0.3f;}
        //if (y>=50){movement = -0.3f;}
        if (x<=-50){movement = 0.3f;}// ive tried setting it using -movement and *= -1 and both result in it not working this is the only way that works that ive tried

        //if (movement<=0 && x<=-50){movement*=-1;}
        if (y>=50){ movement*= -1;}
    }

}

