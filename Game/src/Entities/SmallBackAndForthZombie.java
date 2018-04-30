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
        renderType = Entity.RenderType.Rectangle;
        renderTint = Color.blue;
    }
    private float movement = 0.2f*0.0001f;

    public void update()
    {

        x += movement;
        y -= movement;
        if (x>=50){movement *=-1;}
        //if (movement<=0 && x<=-50){movement*=-1;}
        if (y>=50){ movement*=-1;}


    }

}

