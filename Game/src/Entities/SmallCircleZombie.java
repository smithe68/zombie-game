package Entities;

import Engine.Entity;
import Engine.Input;
import Engine.Updater;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by evan on 4/29/2018.
 */
public class SmallCircleZombie extends Entity {
    private double angle =0;
    public void start()
    {
        renderType = RenderType.Rectangle;
        renderTint = Color.green;
    }


    public void update()
    {

          x = (float) (Math.cos(angle)*100);
          y = (float) (Math.sin(angle)*100);
          angle = angle+0.0000005;
          if (angle == 360){angle = 0;}

    }

}
