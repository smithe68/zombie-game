package Entities;

import Engine.Entity;
import Engine.Renderer;
import Engine.Updater;
import Engine.Visual;

import java.awt.*;

/**
 * Created by evan on 4/29/2018.
 */
public class SmallBackAndForthZombie extends Entity{

    public void start()
    {
        visual.setRenderType(Visual.RenderType.Rectangle);
        visual.setTint(Color.blue);
    }

    private float movement = 0.3f*Updater.deltaTime;

    public void update()
    {

    }

}

