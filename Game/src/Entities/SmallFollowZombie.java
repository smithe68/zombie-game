package Entities;

import Engine.Entity;

import java.awt.*;

/**
 * Created by evan on 4/29/2018.
 */
public class SmallFollowZombie extends Entity {
    Hero hero;
    public void start()
    {
        renderType = Entity.RenderType.Rectangle;
        renderTint = Color.green;


    }


    public void update()
    {


    }
}
