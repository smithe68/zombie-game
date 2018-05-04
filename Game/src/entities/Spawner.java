package entities;

import engine.Entity;
import engine.SceneManager;
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
        timer++;
        if (timer==9000000){
            SceneManager.createEntity(new SmallFollowZombie());
            timer =0;
        }


    }

}
