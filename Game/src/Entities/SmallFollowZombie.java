package Entities;

import Engine.Entity;
import Engine.Mafs;
import Engine.SceneManager;
import Engine.Updater;

import java.awt.*;

/**
 * Created by evan on 4/29/2018.
 */
public class SmallFollowZombie extends Entity
{
    private Hero hero;

    public void start()
    {
        renderType = RenderType.EllipseBorder;
        renderTint = Color.red;

        hero = (Hero)SceneManager.getEntity("Hero");
    }


    public void update()
    {
        x = Mafs.lerp(x, hero.x, Updater.deltaTime * 0.00025f);
        y = Mafs.lerp(y, hero.y, Updater.deltaTime * 0.00025f);
    }
}
