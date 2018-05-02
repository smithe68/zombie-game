package Entities;

import Engine.Entity;
import Engine.SceneManager;

import java.awt.*;

/**
 * Created by evan on 4/29/2018.
 */
public class Spawner extends Entity {
    int timer = 0;
    public void start()
    {
        renderType = RenderType.Rectangle;

    }


    public void update()
    {
        timer++;
        if (timer==9000000){
            SceneManager.createEntity(new SmallFollowZombie());
            timer =0;
        }


    }

}
