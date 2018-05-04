package entities;

import engine.Entity;
import engine.components.Visual;

/**
 * Created by evan on 4/29/2018.
 */
public class Spawner extends Entity
{
    private int timer = 0;

    public void start()
    {
        visual.setRenderType(Visual.RenderType.Rectangle);
    }


    public void update()
    {

    }

}
